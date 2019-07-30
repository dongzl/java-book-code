package com.java.book.self.balking;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:36
 */
public class Singleton {

    private static Singleton singleton;

    private Singleton() {

    }

    public synchronized static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }

        return singleton;
    }
}
