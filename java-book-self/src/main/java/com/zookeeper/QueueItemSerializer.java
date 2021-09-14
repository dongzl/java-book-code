package com.zookeeper;

import org.apache.curator.framework.recipes.queue.QueueSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class QueueItemSerializer implements QueueSerializer<String> {

    // 实例
    public static final QueueItemSerializer INSTANCE = new QueueItemSerializer();

    /**
     * 默认字符集
     */
    private static final Charset charset = StandardCharsets.UTF_8;

    @Override
    public byte[] serialize(String item) {
        return item.getBytes(charset);
    }

    @Override
    public String deserialize(byte[] bytes) {
        return new String(bytes, charset);
    }
}
