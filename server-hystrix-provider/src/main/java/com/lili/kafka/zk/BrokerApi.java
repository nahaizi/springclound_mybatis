package com.lili.kafka.zk;

import org.apache.curator.framework.CuratorFramework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrokerApi {
    /**
     * 根据zk获取Broker ID列表
     * @param zkBean
     * @return
     */
    public static List<String> findBrokerIds(ZKBean zkBean) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            List<String> brokerIdStrs = client.getChildren().forPath(KafkaZKPath.BROKER_IDS_PATH);
            return brokerIdStrs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * 根据zk&brokerId获取Broker信息
     * @param zkBean
     * @return
     */
    public static String findBrokerById(ZKBean zkBean, String id) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            String brokerIdPath = KafkaZKPath.getBrokerSummeryPath(id);
            String brokerInfo = new String(client.getData().forPath(brokerIdPath), "UTF-8");
            return brokerInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    public static Map<String, String> findAllBrokerSummarys(ZKBean zkBean) {
        CuratorFramework client = null;
        try {
            client = ZKConnectionFactory.buildCuratorFramework(zkBean);
            List<String> brokerIds = client.getChildren().forPath(KafkaZKPath.BROKER_IDS_PATH);
            if (brokerIds == null || brokerIds.size() == 0) {
                return null;
            }
            Map<String, String> brokerMap = new HashMap<String, String>();
            for (String brokerId : brokerIds) {
                String brokerIdPath = KafkaZKPath.getBrokerSummeryPath(brokerId);
                String brokerInfo = new String(client.getData().forPath(brokerIdPath), "UTF-8");
                brokerMap.put(brokerId, brokerInfo);
            }
            return brokerMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
