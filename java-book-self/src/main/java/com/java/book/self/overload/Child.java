package com.java.book.self.overload;

import java.io.FileNotFoundException;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 16:58
 */
public class Child extends Parent {

    @Override
    public ChildBean test(Integer a, String b) throws FileNotFoundException {
        return new ChildBean();
    }

    // legal
    /*@Override
    public ChildBean test(Integer a, String b) {
        return new ChildBean();
    }*/

    // not legal
    /*@Override
    public void test(Integer a, String b) {

    }*/

    //Override
    public ChildBean test(String b, Integer a) throws FileNotFoundException {
        return new ChildBean();
    }
}
