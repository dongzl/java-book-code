package com.java.book.self.balking;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:36
 */
public class SingletonV1 {

    private static SingletonV1 singletonV1;

    private SingletonV1() {

    }

    public synchronized static SingletonV1 getInstance() {
        if (singletonV1 == null) {
            singletonV1 = new SingletonV1();
        }

        return singletonV1;
    }
}
