package com.java.book.self;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 16:44
 */
public class OverrideTest {

    public void test1(Integer a, String b) {
        System.out.println("Integer a, String b");
    }

    public void test1(String b, Integer a) {
        System.out.println("String b, Integer a");
    }

    public void test1(Integer a, String b, Long c) {
        System.out.println("Integer a, String b, Long c");
    }

    public void test1(int a, String b) {
        System.out.println("int a, String b");
    }

    //compile error
    /*public Integer test1(Integer a, String b) {
        return 0;
    }*/

    //compile error
    /*public void test1(Integer a, String b) throws Exception {

    }*/

    public static void main(String args[]) throws Exception {
        OverrideTest test = new OverrideTest();
        test.test1(1, "2");
        test.test1(Integer.valueOf(1), "2");
    }
}
