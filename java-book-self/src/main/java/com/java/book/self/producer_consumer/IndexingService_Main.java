package com.java.book.self.producer_consumer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 7.17 测试主程序（ 通过“毒丸”对象来关闭服务）
 * @ClassName: IndexingService_Main
 * @author xingle
 * @date 2014-11-12 下午2:25:36
 */
public class IndexingService_Main {
    public static void main(String[] args) {
        File file = new File("/data");
        IndexingService c = new IndexingService(file);
        c.start();
        try {
            TimeUnit.MICROSECONDS.sleep(100);// 停止ＸＸ时间，显示出消费速度慢于生产速度　
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.stop();
    }
}
