package com.lili.controllor;

import com.lili.rabbitmq.RabbitConfirmConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis提供
 * @author lijunyu
 */
@RestController
@RequestMapping("redisprovider")
public class RedisTestController {
    @Autowired(required = false)
    RabbitTemplate rabbitTemplate;

    @RequestMapping("get/{num}")
    public String get(@PathVariable("num") Integer num) {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("correlationData = " + correlationData);
            if (ack) {
                //如果投递到交换机成功
                System.out.println("消息投递成功!");
                System.out.println("cause1 = " + cause);
            } else {
                //投递到交换机失败，如果投递失败，可以做出处理，例如记录日志，发送短息等...
                System.out.println("消息投递失败!");
                System.out.println("cause2 = " + cause);
            }
        });
        rabbitTemplate.convertAndSend(RabbitConfirmConfig.EXCHANGE_CONFIRM_NAME, RabbitConfirmConfig.CONFIRM_ROUTING_KEY, "message confirm ......");

        return num + "==SUCCEED";
    }

    /**
     * 可靠性投递，回退模式：
     * 1、开启  publisher-returns: true
     * 2、发送消息前先注册回调函数
     * 3、注册 setReturnCallback回调函数，投递到队列失败才执行，如果投递成功到队列则不会执行
     */
    @Test
    void testReturn() {
        //注意，在设置returnCallback的时候，必须要设置交换机处理失败消息的模式，设置为 true
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnsCallback(returned -> {
            /*
             * returned为回退的信息，包括以下几点：
             * message   消息对象
             * replyCode 错误码
             * replyText 错误信息
             * exchange  交换机
             * routingKey 路由键
             */
            System.out.println("message = " + returned.getMessage());
            System.out.println("exchange = " + returned.getExchange());
            System.out.println("replyText = " + returned.getReplyText());
            System.out.println("routingKey = " + returned.getRoutingKey());
            System.out.println("replyCode = " + returned.getReplyCode());
        });
    }

}
