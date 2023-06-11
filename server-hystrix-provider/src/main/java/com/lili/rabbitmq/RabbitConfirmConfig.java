package com.lili.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijunyu
 */
@Configuration
public class RabbitConfirmConfig {
    /**
     * 声明对列
     */
    public static final String QUEUE_CONFIRM_NAME = "test_queue_confirm";
    /**
     * 声明对列
     */
    public static final String QUEUE_CONFIRM_NAME_C = "test_queue_confirm_C";

    /**
     * 声明交换机
     */
    public static final String EXCHANGE_CONFIRM_NAME = "test_exchange_confirm";
    /**
     * 声明交换机
     */
    public static final String EXCHANGE_CONFIRM_NAME_C = "test_exchange_confirm_c";

    /**
     * 声明routing key
     */
    public static final String CONFIRM_ROUTING_KEY_A = "confirm_A";
    public static final String CONFIRM_ROUTING_KEY_B = "confirm_B";
    public static final String CONFIRM_ROUTING_KEY_C = "confirm_C";

    /**
     * 创建队列
     */
    @Bean(name = "confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(QUEUE_CONFIRM_NAME).build();
    }
    @Bean
    public Queue confirmQueueC() {
        return QueueBuilder.durable(QUEUE_CONFIRM_NAME_C).build();
    }

    /**
     * 创建交换机
     */
    @Bean(name = "confirmExchange")
    public Exchange confirmExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_CONFIRM_NAME).durable(true).build();
    }
    /**
     * 创建交换机
     */
    @Bean
    public TopicExchange confirmExchangeC() {
        return ExchangeBuilder.topicExchange(EXCHANGE_CONFIRM_NAME_C).durable(true).build();
    }


    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding confirmBindingA(@Qualifier("confirmQueue") Queue queue,
                                  @Qualifier("confirmExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY_A).noargs();
    }
    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding confirmBindingB(@Qualifier("confirmQueue") Queue queue,
                                  @Qualifier("confirmExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY_B).noargs();
    }

    /**
     * 队列与交换机的绑定，通过routingKey来绑定，fanout模式是不需要routingKey的，因为他是直接绑定队列的
     */
    @Bean
    public Binding confirmBindingC(@Qualifier("confirmQueueC") Queue queue,
                                  @Qualifier("confirmExchangeC") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY_C).noargs();
    }
}
