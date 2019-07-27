package com.java.book.self.two.phase;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-27 21:59
 */
public class Main2 {

    public static void main(String args[]) throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1===a  " + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(20000);
                } catch (Exception e) {
                    System.out.println("thread1===b  " + Thread.currentThread().isInterrupted());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread1.interrupt();
            }
        });


        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
