package com.lili.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = RabbitConfirmConfig.QUEUE_CONFIRM_NAME)
public class ReceiverB extends BaseReceiver{
    @Override
    @RabbitHandler
    public void process(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        System.out.println("BReceiver  : " + hello + "/n");
        manulAck(channel,deliveryTag);
    }
}
