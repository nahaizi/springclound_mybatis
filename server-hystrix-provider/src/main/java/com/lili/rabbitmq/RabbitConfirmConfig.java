package com.lili.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfirmConfig {
    /**
     * 声明对列
     */
    public static final String QUEUE_CONFIRM_NAME = "test_queue_confirm";

    /**
     * 声明交换机
     */
    public static final String EXCHANGE_CONFIRM_NAME = "test_exchange_confirm";

    /**
     * 声明routing key
     */
    public static final String CONFIRM_ROUTING_KEY = "confirm";

    /**
     * 创建队列
     */
    @Bean(name = "confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(QUEUE_CONFIRM_NAME).build();
    }

    /**
     * 创建交换机
     */
    @Bean(name = "confirmExchange")
    public Exchange confirmExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_CONFIRM_NAME).durable(true).build();
    }


    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding confirmBinding(@Qualifier("confirmQueue") Queue queue,
                                  @Qualifier("confirmExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY).noargs();
    }
}
