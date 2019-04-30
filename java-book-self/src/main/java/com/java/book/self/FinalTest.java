package com.java.book.self;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 17:04
 */
public final class FinalTest {

    private final int a = 100;

    private final int b;

    public FinalTest(int b) {
        this.b = b;
    }

    public final void test1() {

    }

    public static void main(String args[]) throws Exception {
        FinalTest test = new FinalTest(2);
        //test.a = 200; compile error
    }
}
