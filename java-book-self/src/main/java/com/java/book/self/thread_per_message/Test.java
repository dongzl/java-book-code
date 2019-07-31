package com.java.book.self.thread_per_message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:50
 */
public class Test {

    public static void main(String args[]) throws Exception {
        //L1、L2 阶段共用的线程池
        ExecutorService es = Executors.newFixedThreadPool(10);
        //L1 阶段的闭锁
        CountDownLatch l1 = new CountDownLatch(2);
        for (int i=0; i<2; i++){
            System.out.println("L1");
            // 执行 L1 阶段任务
            es.execute(()->{
                //L2 阶段的闭锁
                CountDownLatch l2 = new CountDownLatch(2);
                // 执行 L2 阶段子任务
                for (int j = 0; j < 2; j++){
                    es.execute(()->{
                        System.out.println("L2");
                        l2.countDown();
                    });
                }
                // 等待 L2 阶段任务执行完
                try {
                    l2.await();
                } catch (Exception e) {

                }
                l1.countDown();
            });
        }
        // 等着 L1 阶段任务执行完
        l1.await();
        System.out.println("end");
    }
}
