package com.java.book.self.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-14 17:36
 */
public class TransmittableThreadLocalTest {

    private static ThreadLocal<String> parent = new TransmittableThreadLocal<String>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String args[]) throws Exception {
        parent.set("value-set-in-parent");
        System.out.println(parent.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = parent.get();
                System.out.println(value);
            }
        });

        executorService.execute(thread);
        executorService.execute(thread);
        executorService.shutdown();
    }
}
