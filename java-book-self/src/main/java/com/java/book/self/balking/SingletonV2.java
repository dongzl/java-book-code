package com.java.book.self.balking;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:36
 */
public class SingletonV2 {

    // 需要volatile关键字，new SingletonV2() 可能多条指令完成，防止指令重排
    private static volatile SingletonV2 singleton;

    private SingletonV2() {

    }

    public static SingletonV2 getInstance() {
        if (singleton == null) {
            synchronized (SingletonV2.class) {
                if (singleton == null) {
                    singleton = new SingletonV2();
                }
            }
        }

        return singleton;
    }
}
