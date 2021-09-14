package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/1/15 3:35 下午
 */
public class ZookeeperDistributedDelayQueueTest {

    private static final int baseSleepTimeMs = 1000, maxRetries = 3, connectionTimeoutMs = 10000, sessionTimeoutMs = 10000;

    static final CuratorFramework FRAMEWORK = CuratorFrameworkFactory
            .newClient("127.0.0.1:2181", connectionTimeoutMs, sessionTimeoutMs, new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries));

    static {
        FRAMEWORK.start();
    }

    public static void main(String[] args) throws Exception {
        String value = "test_%s";
        for (int i = 0; i < 1000; i++) {
            FRAMEWORK.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath("/jdread/app/first_login/queue-", ItemSerializer.serialize(String.format(value, i)));
        }
    }
}
