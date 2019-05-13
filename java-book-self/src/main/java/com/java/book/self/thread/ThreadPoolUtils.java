package com.java.book.self.thread;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 * Created by dongzonglei on 2016/11/10.
 */
public final class ThreadPoolUtils {

    /**
     * 线程池线程数
     */
    private static int THREAD_POOL_SIZE = 10;

    /**
     * 创建线程池
     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private ThreadPoolUtils(){

    }

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public static void submit(Runnable runnable) {
        executor.submit(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        return executor.submit(callable);
    }

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws Exception {
        return executor.invokeAll(tasks);
    }
}
