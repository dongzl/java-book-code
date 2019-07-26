package com.java.book.self.guarded;

import java.util.Random;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 10:36
 */
public class ClientThread extends Thread {

    private Random random;

    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++) {
            Request request = new Request("NO." + i);
            System.out.println(Thread.currentThread().getName() + " Request " + request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }
}
