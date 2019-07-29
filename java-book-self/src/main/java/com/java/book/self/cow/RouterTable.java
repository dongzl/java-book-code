package com.java.book.self.cow;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-29 18:38
 */
public class RouterTable {

    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

    public Set<Router> get(String iface) {
        return rt.get(iface);
    }

    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);
        }
    }

    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<>());
        set.add(router);
    }
}
