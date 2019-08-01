package com.java.book.self.producer_consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-01 16:32
 */
public class Main {

    // 任务队列
    BlockingQueue<Task> bq = new LinkedBlockingQueue<>(2000);

    // 启动 5 个消费者线程
    // 批量执行任务
    void start() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++) {
            es.execute(() -> {
                try {
                    while (true) {
                        // 获取批量任务
                        List<Task> ts = pollTask();
                        // 执行批量任务
                        execTask(ts);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    // 批量执行任务
    private void execTask(List<Task> ts) {

    }

    // 从任务队列中获取批量任务
    private List<Task> pollTask() throws InterruptedException {
        List<Task> ts = new LinkedList<>();
        // 阻塞式获取第一条任务
        Task t = bq.take();
        while (t != null) {
            ts.add(t);
            // 非阻塞式获取第一条任务
            t = bq.poll();
        }
        return ts;
    }


}
