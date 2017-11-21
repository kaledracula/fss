package com.cony.mq.sender;

import com.cony.mq.common.Constants;
import com.cony.mq.common.DefaultMQResult;
import com.cony.mq.common.MQResult;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangk-p on 2017/4/19.
 */
@SuppressWarnings("ALL")
public class RabbitSenderBuilder {

    private static final Logger logger = LoggerFactory.getLogger(RabbitSenderBuilder.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private RabbitSenderBuilder() {
    }

    private static class RabbitSenderBuilderHolder {
        private static RabbitSenderBuilder rabbitSenderBuilder = new RabbitSenderBuilder();
    }

    public static RabbitSenderBuilder getInstance() {
        return RabbitSenderBuilderHolder.rabbitSenderBuilder;
    }

    public MessageSender buildMessageSender(final String exchange, final String routingKey, final String queue) throws IOException {
        return buildMessageSender(exchange, routingKey, queue, "direct");
    }

    public MessageSender buildTopicMessageSender(final String exchange, final String routingKey) throws IOException {
        return buildMessageSender(exchange, routingKey, null, "topic");
    }


    private MessageSender buildMessageSender(final String exchange, final String routingKey, final String queue,final String type) throws IOException{
        Connection connection = connectionFactory.createConnection();
        if (type.equals("direct")) {
            buildQueue(exchange, routingKey, queue, connection, "direct");
        } else if (type.equals("topic")) {
            buildTopic(exchange, connection);
        }
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        RetryCache retryCache = new RetryCache();
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                logger.info("发送消息失败，消息ID：" + correlationData.toString()+" 原因："+ cause );
            } else {
                retryCache.del(correlationData.getId());
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange, tmpRoutingKey) -> {
            try {
                Thread.sleep(Constants.ONE_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("发送消息失败: " + replyCode + " " + replyText);
            rabbitTemplate.send(message);
        });

        return new MessageSender() {
            {
                retryCache.setSender(this);
            }

            @Override
            public MQResult send(Object message) {
                try {
                    String id = retryCache.generateId();
                    retryCache.add(id, message);
                    rabbitTemplate.correlationConvertAndSend(message, new CorrelationData(id));
                } catch (Exception e) {
                    return new DefaultMQResult().addError(e.getMessage());
                }

                return new DefaultMQResult().addMessage("");
            }
        };
    }

    private void buildQueue(String exchange, String routingKey,
                            final String queue, Connection connection, String type) throws IOException {
        Channel channel = connection.createChannel(false);
        if (type.equals("direct")) {
            channel.exchangeDeclare(exchange, "direct", true, false, null);
        } else if (type.equals("topic")) {
            channel.exchangeDeclare(exchange, "topic", true, false, null);
        }
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);
        try {
            channel.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.info("close channel time out " + e);
        }
    }

    private void buildTopic(String exchange, Connection connection) throws IOException {
        Channel channel = connection.createChannel(false);
        channel.exchangeDeclare(exchange, "topic", true, false, null);
    }


    public int getMessageCount(final String queue) throws IOException {
        Connection connection = connectionFactory.createConnection();
        final Channel channel = connection.createChannel(false);
        final AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queue);
        return declareOk.getMessageCount();
    }
}
