package com.java.book.self.producer_consumer;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-01 16:44
 */
public class Logger {

    // 任务队列
    final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue<>();

    //flush 批量
    static final int batchSize = 500;

    // 只需要一个线程写日志
    ExecutorService es = Executors.newFixedThreadPool(1);

    // 启动写日志
    void start() throws Exception {
        File file = File.createTempFile("foo", ".log");
        final FileWriter writer = new FileWriter(file);
        this.es.execute(() -> {
            try {
                // 未刷盘日志数量
                int curIdx = 0;
                long preFT = System.currentTimeMillis();
                while (true) {
                    LogMsg log = bq.poll(5, TimeUnit.SECONDS);
                    // 写日志
                    if (log != null) {
                        writer.write(log.toString());
                        ++curIdx;
                    }
                    // 如果不存在未刷盘数据，则无需刷盘
                    if (curIdx <= 0) {
                        continue;
                    }
                    // 判断刷盘规则
                    if (log != null && log.level == LEVEL.ERROR || curIdx == batchSize || System.currentTimeMillis() - preFT > 5000) {
                        writer.flush();
                        curIdx = 0;
                        preFT = System.currentTimeMillis();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class LogMsg {
        LEVEL level;
        String msg;

        public LogMsg(LEVEL level, String msg) {
            this.level = level;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "LogMsg{" +
                    "level=" + level +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    // 日志级别
    enum LEVEL {
        INFO, ERROR
    }
}
