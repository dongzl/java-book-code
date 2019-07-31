package com.java.book.self.thread_per_message;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:04
 */
public class EchoV4 {

    public static void main(String args[]) throws Exception {
        ExecutorService es = new ThreadPoolExecutor(50, 500, 60L, TimeUnit.SECONDS,
                // 注意创建有界队列
                new LinkedBlockingQueue<Runnable>(2000),
                // 建议根据业务需求实现 ThreadFactory
                r -> {
                    return new Thread(r, "echo-" + r.hashCode());
                },
                // 建议根据业务需求实现 RejectedExecutionHandler
                new ThreadPoolExecutor.CallerRunsPolicy());
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
