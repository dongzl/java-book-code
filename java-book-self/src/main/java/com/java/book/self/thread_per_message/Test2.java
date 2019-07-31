package com.java.book.self.thread_per_message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:50
 */
public class Test2 {

    public static void main(String args[]) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.submit(() -> {
            try {
                String qq = pool.submit(() -> "QQ").get();
                System.out.println(qq);
            } catch (Exception e) {
            }
        });
    }
}
