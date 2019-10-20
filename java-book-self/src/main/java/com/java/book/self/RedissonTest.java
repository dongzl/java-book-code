package com.java.book.self;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-10 14:05
 */
public class RedissonTest {

    public static void main(String args[]) throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.120.0:5378").setPassword("123456").setDatabase(0);
        RedissonClient client = Redisson.create(config);

        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://192.168.120.1:5378").setPassword("123456").setDatabase(0);
        RedissonClient client1 = Redisson.create(config1);

        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://192.168.120.2:5378").setPassword("123456").setDatabase(0);
        RedissonClient client2 = Redisson.create(config2);

        String key = "SIGN_GET_GIFT_userId";
        RLock rLock = client.getLock(key);
        RLock rLock1 = client1.getLock(key);
        RLock rLock2 = client2.getLock(key);
        RedissonRedLock redLock = new RedissonRedLock(rLock, rLock1, rLock2);
        try {
            if (!redLock.tryLock()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }
}
