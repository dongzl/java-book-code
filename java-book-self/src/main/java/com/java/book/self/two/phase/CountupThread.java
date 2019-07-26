package com.java.book.self.two.phase;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 16:54
 */
public class CountupThread extends Thread {

    private long counter = 0;

    private volatile boolean shutdownRequested = false;

    public void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public final void run() {
        try {
            while (!shutdownRequested) {
                doWork();
            }
        } catch (InterruptedException e) {

        } finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
