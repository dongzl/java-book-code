package com.java.book.self.guarded;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 10:37
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() {
        while (queue.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        return queue.removeFirst();
    }

    public synchronized void putRequest(Request r) {
        queue.addLast(r);
        notifyAll();
    }
}
