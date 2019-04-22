package com.java.book.self;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 17:04
 */
public final class FinalTest {

    private final int a = 100;

    public final void test1() {

    }

    public static void main(String args[]) throws Exception {
        FinalTest test = new FinalTest();
        //test.a = 200; compile error
    }
}
