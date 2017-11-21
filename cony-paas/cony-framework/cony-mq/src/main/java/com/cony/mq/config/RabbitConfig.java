package com.cony.mq.config;


import com.cony.mq.consumer.RabbitConsumerBuilder;
import com.cony.mq.sender.RabbitSenderBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by wangk-p on 2017/4/18.
 */
@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public RabbitSenderBuilder rabbitSenderBuilder() {
        return RabbitSenderBuilder.getInstance();
    }

    @Bean
    public RabbitConsumerBuilder rabbitConsumerBuilder() {
        return RabbitConsumerBuilder.getInstance();
    }

}
