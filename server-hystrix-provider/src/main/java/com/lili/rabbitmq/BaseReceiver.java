package com.lili.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

public abstract class BaseReceiver {

    public void manulAck(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        //手动Ack
        /**
         * 手动Ack参数说明
         * basicAck(long deliveryTag, boolean multiple)
         * deliveryTag：批量处理的标号，举例：这个队列现在有5条消息要消费，那么这批数据会标号从1-5递增，5的时候就会手动Ack  multiple：是否批量处理
         *
         */
        channel.basicAck(deliveryTag,true);
    }
    public abstract void process(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException;
}

