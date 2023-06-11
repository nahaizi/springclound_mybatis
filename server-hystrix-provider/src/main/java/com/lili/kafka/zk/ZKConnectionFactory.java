package com.lili.kafka.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZKConnectionFactory {
    public static CuratorFramework buildCuratorFramework(ZKBean zk) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zk.getZkConnectionTimeout() * 1000, zk.getMaxRetryCount());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zk.getZkURL(), retryPolicy);
        client.start();
        return client;
    }
}
