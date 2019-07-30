package com.java.book.self.balking;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-30 15:34
 */
public class InitTest {

    boolean inited = false;

    synchronized void init() {
        if (inited) {
            return;
        }

        //doInit();
        inited = true;
    }
}
