package com.java.book.self.thread_per_message;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-31 15:23
 */
public class Main {

    public static void main(String args[]) throws Exception {
        System.out.println("main BEGIN");
        Host host = new Host();
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("main END");
    }
}
