package com.java.book.self.balking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:17
 */
public class AutoSaveEditor {

    // 文件是否被修改过
    boolean changed = false;

    // 定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    // 定时执行自动保存
    void startAutoSave() {
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }

    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }

            changed = false;
        }

        // 执行存盘操作
        this.execSave();
    }

    // 编辑操作
    void execSave() {
        synchronized (this) {
            changed = true;
        }
    }
}
