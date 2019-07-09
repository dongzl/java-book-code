package com.java.book.self.queue;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-09 15:40
 */
public final class Node {

    public final boolean isData;

    public volatile Object item;

    public volatile Node next;

    public volatile Thread waiter;

    private static final sun.misc.Unsafe UNSAFE;

    private static final long itemOffset;

    private static final long nextOffset;

    private static final long waiterOffset;

    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = Node.class;
            itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
            nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
            waiterOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiter"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public final boolean casNext(Node cmp, Node val) {
        return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
    }

    public final boolean casItem(Object cmp, Object val) {
        return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
    }

    public Node(Object item, boolean isData) {
        UNSAFE.putObject(this, itemOffset, item); // relaxed write
        this.isData = isData;
    }

    public final void forgetNext() {
        UNSAFE.putObject(this, nextOffset, this);
    }

    public final void forgetContents() {
        UNSAFE.putObject(this, itemOffset, this);
        UNSAFE.putObject(this, waiterOffset, null);
    }

    public final boolean isMatched() {
        Object x = item;
        return (x == this) || ((x == null) == isData);
    }

    public final boolean isUnmatchedRequest() {
        return !isData && item == null;
    }

    public final boolean cannotPrecede(boolean haveData) {
        boolean d = isData;
        Object x;
        return d != haveData && (x = item) != this && (x != null) == d;
    }

    public final boolean tryMatchData() {
        Object x = item;
        if (x != null && x != this && casItem(x, null)) {
            LockSupport.unpark(waiter);
            return true;
        }
        return false;
    }
}
