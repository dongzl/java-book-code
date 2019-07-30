package com.java.book.self.balking;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:36
 */
public class Singleton {

    // 需要volatile关键字，new Singleton() 可能多条指令完成，防止指令重排
    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
