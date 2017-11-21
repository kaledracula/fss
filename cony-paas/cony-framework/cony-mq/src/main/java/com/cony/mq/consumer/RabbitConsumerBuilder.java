package com.cony.mq.consumer;

import com.cony.mq.common.Constants;
import com.cony.mq.common.DefaultMQResult;
import com.cony.mq.common.MQResult;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.support.ConsumerCancelledException;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangk-p on 2017/4/19.
 */
@SuppressWarnings("ALL")
public class RabbitConsumerBuilder {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumerBuilder.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    private RabbitConsumerBuilder() {}

    private static class RabbitConsumerBuilderHolder {
        private static RabbitConsumerBuilder rabbitConsumerBuilder = new RabbitConsumerBuilder();
    }

    public static RabbitConsumerBuilder getInstance() {
        return RabbitConsumerBuilderHolder.rabbitConsumerBuilder;
    }

    public <T> MessageConsumer buildMessageConsumer(String exchange, String routingKey, final String queue,
                                                    final MessageProcessor<T> messageProcessor) throws IOException {
        return buildMessageConsumer(exchange, routingKey, queue, messageProcessor, "direct");
    }

    public <T> MessageConsumer buildTopicMessageConsumer(String exchange, String routingKey, final String queue,
                                                         final MessageProcessor<T> messageProcessor) throws IOException {
        return buildMessageConsumer(exchange, routingKey, queue, messageProcessor, "topic");
    }

    //1 创建连接和channel
    //2 设置message序列化方法
    //3 构造consumer
    private <T> MessageConsumer buildMessageConsumer(String exchange, String routingKey, final String queue,
                                                    final MessageProcessor<T> messageProcess, String type) throws IOException {
        final Connection connection = connectionFactory.createConnection();
        buildQueue(exchange, routingKey, queue, connection, type);
        final MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
        final MessageConverter messageConverter = new Jackson2JsonMessageConverter();
        return new MessageConsumer() {
            QueueingConsumer consumer;

            {
                consumer = buildQueueConsumer(connection, queue);
            }

            @Override
            //1 通过delivery获取原始数据
            //2 将原始数据转换为特定类型的包
            //3 处理数据
            //4 手动发送ack确认
            public MQResult consume() {
                QueueingConsumer.Delivery delivery = null;
                Channel channel = consumer.getChannel();
                try {
                    delivery = consumer.nextDelivery();
                    Message message = new Message(delivery.getBody(),
                            messagePropertiesConverter.toMessageProperties(delivery.getProperties(), delivery.getEnvelope(), "UTF-8"));

                    @SuppressWarnings("unchecked")
                    T messageBean = (T) messageConverter.fromMessage(message);
                    MQResult result = new DefaultMQResult();
                    try {
                        result = messageProcess.process(messageBean);
                    } catch (Exception e) {
                        result = new DefaultMQResult().addError(e.getMessage());
                    }
                    if (result.isSuccess()) {
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    } else {
                        Thread.sleep(Constants.ONE_SECOND);
                        logger.info("process message failed: " + result.getErrors());
                        channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
                    }
                    return result;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return new DefaultMQResult();
                } catch (ShutdownSignalException | ConsumerCancelledException | IOException e) {
                    e.printStackTrace();
                    try {
                        channel.close();
                    } catch (IOException | TimeoutException ex) {
                        ex.printStackTrace();
                    }
                    consumer = buildQueueConsumer(connection, queue);
                    return new DefaultMQResult();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("exception : " + e);

                    try {
                        channel.close();
                    } catch (IOException | TimeoutException ex) {
                        ex.printStackTrace();
                    }
                    consumer = buildQueueConsumer(connection, queue);
                    return new DefaultMQResult();
                }
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

    private QueueingConsumer buildQueueConsumer(Connection connection, String queue) {
        try {
            Channel channel = connection.createChannel(false);
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //通过 BasicQos 方法设置prefetchCount = 1。这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message。
            //换句话说，在接收到该Consumer的ack前，他它不会将新的Message分发给它
            channel.basicQos(1);
            channel.basicConsume(queue, false, consumer);
            return consumer;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("build queue consumer error : " + e);
            try {
                Thread.sleep(Constants.ONE_SECOND);
            } catch (InterruptedException inE) {
                inE.printStackTrace();
            }
            return buildQueueConsumer(connection, queue);
        }
    }
}
