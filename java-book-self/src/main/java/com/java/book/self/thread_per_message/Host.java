package com.java.book.self.thread_per_message;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:21
 */
public class Host {

    private final Helper helper = new Helper();

    public void request(final int count, final char c) {
        System.out.println("request(" + count + ", " + c + ") BEGIN");
        new Thread() {
            public void run() {
                helper.handle(count, c);
            }
        }.start();
        System.out.println("request(" + count + ", " + c + ") END");
    }
}
