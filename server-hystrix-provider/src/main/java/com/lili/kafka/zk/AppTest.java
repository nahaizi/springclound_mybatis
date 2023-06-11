package com.lili.kafka.zk;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppTest {

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

    public static void main(String[] args) {
        ZKBean zk = new ZKBean();
        zk.setZkURL("127.0.0.1:2181");
        List<String> ids = BrokerApi.findBrokerIds(zk);
        for (String id : ids) {
            String idInfo = BrokerApi.findBrokerById(zk, id);
            System.out.println(idInfo);
        }

        Map<String, String> param = new HashMap<>(8);
        param.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        param.put(ProducerConfig.ACKS_CONFIG, ACKS_CONFIG);
        param.put(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE_CONFIG);
        param.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        param.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        TopicApi.createTopic(zk,"test1",1,3,param);


        List<String> topicNames = TopicApi.findAllTopicName(zk);
        for (String topicName : topicNames) {
            System.out.println("topicName:" + topicName);
        }

        List<String> topics = TopicApi.findAllTopics(zk);
        for (String topic : topics) {
            System.out.println("topic:" + topic);
        }

    }
}
