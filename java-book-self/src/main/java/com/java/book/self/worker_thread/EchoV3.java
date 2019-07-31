package com.java.book.self.worker_thread;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:04
 */
public class EchoV3 {

    public static void main(String args[]) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(500);
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        // 处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 每个请求创建一个线程
                es.execute(() -> {
                    try {
                        // 读 Socket
                        ByteBuffer rb = ByteBuffer.allocate(1024);
                        sc.read(rb);
                        // 模拟处理请求
                        Thread.sleep(2000);
                        // 写 Socket
                        ByteBuffer wb = (ByteBuffer)rb.flip();
                        sc.write(wb);
                        // 关闭 Socket
                        sc.close();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                });
            }
        } finally {
            ssc.close();
        }
    }
}
