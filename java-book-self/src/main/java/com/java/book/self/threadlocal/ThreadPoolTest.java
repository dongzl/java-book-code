package com.java.book.self.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池线程复用
 * @author dongzonglei
 * @description
 * @date 2019-05-14 18:36
 */
public class ThreadPoolTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getId() + "_" + Thread.currentThread().getName());
            });
        }
    }
}
