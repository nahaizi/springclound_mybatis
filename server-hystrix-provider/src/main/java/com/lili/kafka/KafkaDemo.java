package com.lili.kafka;

public class KafkaDemo {
    public static void main(String[] args) {
        // 启动生产者，发送两条消息
       /* Thread e = new Thread(new KafkaProvicer());
        e.setName("start---");
        e.start();*/
        // A、B都在消费者组A下，故消息要么被A消费，要么被B消费
        // 启动消费者A
        Thread e1 = new Thread(new KafKaCustomer(KafKaCustomer.GROUP_ID_A, KafKaCustomer.CLIENT_ID_A));
        e1.setName("AA");
        e1.start();
        // 启动消费者B
        Thread e2 = new Thread(new KafKaCustomer(KafKaCustomer.GROUP_ID_A, KafKaCustomer.CLIENT_ID_B));
        e2.setName("BB");
        e2.start();

        // 启动消费者C，在消费者组B下，可以消费到两条消息
        Thread e3 = new Thread(new KafKaCustomer(KafKaCustomer.GROUP_ID_B, KafKaCustomer.CLIENT_ID_C));
        e3.setName("CC");
        e3.start();


    }
}
