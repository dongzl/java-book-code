package com.java.book.self.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author dongzonglei
 * @description
 * @date 2019-09-16 11:14
 */
public class ZookeeperSimpleDistributedQueueMain {

    public static void main(String args[]) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        String lockPath = "/zkLockRoot/lock_1";
        SimpleDistributedQueue simpleDistributedQueue = new SimpleDistributedQueue(client, lockPath);
        ExecutorService executor = Executors.newCachedThreadPool();
        BiConsumer<SimpleDistributedQueue, String> putConsumer = (queue, item) -> {
            try {
                Callable<Boolean> call = () -> {
                    try {
                        TimeUnit.SECONDS.sleep((int)Math.random() * 10);
                        queue.offer(item.getBytes());
                        System.out.println(Thread.currentThread() + " put: " + item);
                    } catch (Exception e) {

                    }
                    return true;
                };
                executor.submit(call);
            } catch (Exception e) {

            }
        };

        //消费
        Consumer<SimpleDistributedQueue> getConsumer = (queue) -> {
            try {
                Callable<Boolean> call = () -> {
                    try {
                        while(true){
                            //从队首取出数据
                            byte[] dataByte = queue.take();
                            String data = new String(dataByte);
                            if(Objects.isNull(data)){
                                break;
                            }
                            System.out.println(Thread.currentThread() + "  get : "+ data);
                        }
                    } catch (Exception e) {
                    }
                    return true;
                };
                executor.submit(call);
            } catch (Exception e) {
            }
        };

        //分布式队列测试(5个线程生产)
        System.out.println("5个并发线程生产,测试分布式队列");
        //5个生产线程
        for (int i = 0; i < 10; i++) {
            putConsumer.accept(simpleDistributedQueue, "item"+i);
        }
        //分布式队列测试(2个线程生产)
        System.out.println("2个并发线程消息,测试分布式队列");
        //2个消费线程
        for (int i = 0; i < 2; i++) {
            getConsumer.accept(simpleDistributedQueue);
        }

        executor.shutdown();
        TimeUnit.SECONDS.sleep(20);
        client.close();
    }
}
