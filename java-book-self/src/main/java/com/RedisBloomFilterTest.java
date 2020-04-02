package com;

import io.rebloom.client.Client;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/21 下午10:11
 */
public class RedisBloomFilterTest {
    
    public static void main(String[] args) {
        Client client = new Client("192.168.202.121", 6395);
        client.add("test", "1");
        client.add("test", "2");
        System.out.println(client.exists("test", "2"));
        System.out.println(client.exists("test", "3"));
    }
}
