package com.java.book.self.balking;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-29 18:38
 */
public class RouterTable {

    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

    volatile boolean changed;

    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public void startLocalServer() {
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 1, 1, TimeUnit.SECONDS);
    }

    void autoSave() {
        if (!changed) {
            return;
        }

        changed = false;

        //this.save2Local();
    }

    public Set<Router> get(String iface) {
        return rt.get(iface);
    }

    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);

            changed = true;
        }
    }

    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<>());
        set.add(router);

        changed = true;
    }
}
