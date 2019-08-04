package com.java.book.self.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-04 16:18
 */
public class RateLimiterTest {

    public static void main(String args[]) throws Exception {
        RateLimiter limiter = RateLimiter.create(2.0);
        ExecutorService es = Executors.newFixedThreadPool(1);
        final long prev = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            limiter.acquire();
            es.execute(() -> {
                long cur = System.nanoTime();
                System.out.println((cur - prev) / 1000_000);
                //prev = cur;
            });
        }
    }
}
