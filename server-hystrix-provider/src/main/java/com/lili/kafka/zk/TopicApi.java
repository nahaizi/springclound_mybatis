package com.lili.kafka.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicApi {
    /**
     * 创建Topic
     * @param zkBean
     * @param topic
     * @param partitionsCount
     * @param replicationFactorCount
     * @param topicConfig
     */
    public static void createTopic(ZKBean zkBean, String topic, int partitionsCount, int replicationFactorCount,
                                   Map<String, String> topicConfig) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            List<String> brokerIds = client.getChildren().forPath(KafkaZKPath.BROKER_IDS_PATH);
            List<Integer> ids = new ArrayList<>();
            for (String str : brokerIds) {
                int i = Integer.parseInt(str);
                ids.add(i);
            }
            Map<String, List<Integer>> replicasAssignment = ReplicasAssignmentBuilder.assignReplicasToBrokers(ids, partitionsCount,
                    replicationFactorCount);
            Map<String, Object> topicSummaryMap = new HashMap<>();
            topicSummaryMap.put("version", 1);
            topicSummaryMap.put("partitions", replicasAssignment);
            byte[] topicSummaryData = objectToByteArray(topicSummaryMap);
            String topicSummaryPath = KafkaZKPath.getTopicSummaryPath(topic);
            Stat stat = client.checkExists().forPath(topicSummaryPath);
            if (stat == null) {
                // create
                client.create().creatingParentsIfNeeded().forPath(topicSummaryPath, topicSummaryData);
            } else {
                // update
                client.setData().forPath(topicSummaryPath, topicSummaryData);
            }

            Map<String, Object> topicConfigMap = new HashMap<>();
            topicConfigMap.put("version", 1);
            topicConfigMap.put("config", topicConfig);
            byte[] topicConfigData = objectToByteArray(topicConfigMap);

            String topicConfigPath = KafkaZKPath.getTopicConfigPath(topic);
            Stat stat2 = client.checkExists().forPath(topicConfigPath);
            if (stat2 == null) {
                // create
                client.create().creatingParentsIfNeeded().forPath(topicConfigPath, topicConfigData);
            } else {
                // update
                client.setData().forPath(topicConfigPath, topicConfigData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * 删除Topic
     * @param zkBean
     * @param topicName
     */
    public static void deleteTopic(ZKBean zkBean, String topicName) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            String adminDeleteTopicPath = KafkaZKPath.getDeleteTopicPath(topicName);
            client.create().creatingParentsIfNeeded().forPath(adminDeleteTopicPath, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * 查询topic
     * @param zkBean
     * @return
     */
    public static List<String> findAllTopics(ZKBean zkBean) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            List<String> topicNames = client.getChildren().forPath(KafkaZKPath.BROKER_TOPICS_PATH);
            List<String> topicSummarys = new ArrayList<String>();
            for (String topicName : topicNames) {
                byte[] topicData = client.getData().forPath(KafkaZKPath.getTopicSummaryPath(topicName));
                topicSummarys.add(new String(topicData, "UTF-8"));
            }
            // add delete flag
            List<String> deleteTopics = client.getChildren().forPath(KafkaZKPath.ADMIN_DELETE_TOPICS_PATH);
            return topicSummarys;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    public static List<String> findAllTopicName(ZKBean zkBean) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            return client.getChildren().forPath(KafkaZKPath.BROKER_TOPICS_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
    public static byte[] objectToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
}
