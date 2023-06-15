package com.lili.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String context = "hi, fanout msg " + i;
            System.out.println("Sender : " + context);
            this.rabbitTemplate.convertAndSend(RabbitConfirmConfig.EXCHANGE_CONFIRM_NAME,
                    RabbitConfirmConfig.CONFIRM_ROUTING_KEY_A, context + "A");
            this.rabbitTemplate.convertAndSend(RabbitConfirmConfig.EXCHANGE_CONFIRM_NAME,
                    RabbitConfirmConfig.CONFIRM_ROUTING_KEY_B, context + "B");
            this.rabbitTemplate.convertAndSend(RabbitConfirmConfig.EXCHANGE_CONFIRM_NAME_C,
                    RabbitConfirmConfig.CONFIRM_ROUTING_KEY_C, context + "C");
            Thread.sleep(2000);
        }
    }
}
