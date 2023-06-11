package com.lili.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaProvicer implements Runnable {

    private static final String TOPIC = "TEST_TOPIC_LILI";

    private static final String BROKER_LIST = "localhost:9092,localhost:9093,localhost:9094,localhost:9095";

    /**
     * 消息发送确认
     * 0，只要消息提交到消息缓冲，就视为消息发送成功
     * 1，只要消息发送到分区Leader且写入磁盘，就视为消息发送成功
     * all，消息发送到分区Leader且写入磁盘，同时其他副本分区也同步到磁盘，才视为消息发送成功
     */
    private static final String ACKS_CONFIG = "all";

    /**
     * 缓存消息数达到此数值后批量提交
     */
    private static final String BATCH_SIZE_CONFIG = "1";

    private static KafkaProducer<String, String> producer;

    static {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.setProperty(ProducerConfig.ACKS_CONFIG, ACKS_CONFIG);
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE_CONFIG);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void run() {
        try {
            // 等待启动日志打印完后再发送消息
            AtomicInteger at = new AtomicInteger(1);
            String message = "消息产生者-This is a message ==>";
            while(true) {
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC,"demo", message + at.getAndIncrement() );
                producer.send(record, (recordMetadata, e) -> {
                    if (e != null) {
                        System.out.println("发送消息异常！");
                    }
                    if (recordMetadata != null) {
                        // topic 下可以有多个分区，每个分区的消费者维护一个 offset
                        System.out.println("消息发送成功：" + recordMetadata.partition() + "-" + recordMetadata.offset());
                    }
                });
                Thread.sleep(5000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
