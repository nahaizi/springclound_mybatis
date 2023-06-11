package com.lili.kafka.zk;

public class KafkaZKPath {

    public static final String COMSUMERS_PATH = "/consumers";
    public static final String BROKER_IDS_PATH = "/brokers/ids";
    public static final String BROKER_TOPICS_PATH = "/brokers/topics";
    public static final String CONFIG_TOPICS_PATH = "/config/topics";
    public static final String CONFIG_CHANGES_PATH = "/config/changes";
    public static final String CONTROLLER_PATH = "/controller";
    public static final String CONTROLLER_EPOCH_PATH = "/controller_epoch";
    public static final String ADMIN_PATH = "/admin";
    public static final String ADMIN_REASSGIN_PARTITIONS_PATH = "/admin/reassign_partitions";
    public static final String ADMIN_DELETE_TOPICS_PATH = "/admin/delete_topics";
    public static final String ADMIN_PREFERED_REPLICA_ELECTION_PATH = "/admin/preferred_replica_election";

    public static String getTopicSummaryPath(String topic) {
        return BROKER_TOPICS_PATH + "/" + topic;
    }

    public static String getTopicPartitionsStatePath(String topic,String partitionId) {
        return getTopicSummaryPath(topic) + "/partitions/" + partitionId+ "/state";
    }

    public static String getTopicConfigPath(String topic) {
        return CONFIG_TOPICS_PATH + "/" + topic;
    }

    public static String getDeleteTopicPath(String topic) {
        return ADMIN_DELETE_TOPICS_PATH + "/" + topic;
    }

    public static String getBrokerSummeryPath(String id) {
        return BROKER_IDS_PATH + "/" + id;
    }

    public static String getConsumerOffsetPath(String groupId) {
        return  COMSUMERS_PATH + "/" + groupId +"/offsets";
    }

    public static String getTopicConsumerOffsetPath(String groupId,String topic){
        return  COMSUMERS_PATH + "/" + groupId +"/offsets/"+topic;
    }

    public static String getTopicPartitionConsumerOffsetPath(String groupId, String topic,String partitionId){
        return  COMSUMERS_PATH + "/" + groupId +"/offsets/"+topic + "/" + partitionId;
    }

    public static String getTopicPartitionConsumerOwnerPath(String groupId, String topic, String partitionId) {
        return  COMSUMERS_PATH + "/" + groupId +"/owners/"+topic + "/" + partitionId;
    }

}
