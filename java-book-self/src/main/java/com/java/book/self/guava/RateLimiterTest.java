package com.java.book.self.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-31 15:11
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        RateLimiter limiter = RateLimiter.create(3); // 这里的1表示每秒允许处理的量为1个
        for (int i = 1; i <= 10; i++) {
            limiter.acquire(1);// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
//            if (limiter.tryAcquire(200, TimeUnit.MICROSECONDS)) {
//                System.out.println("call execute.." + i);
//            } else {
//                System.out.println("call execute..error");
//            }
        }
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("start time:" + start);
        System.out.println("end time:" + end);
    }
}
