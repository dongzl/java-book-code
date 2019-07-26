package com.java.book.self.guarded;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 10:44
 */
public class Main {

    public static void main(String args[]) throws Exception {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }
}
