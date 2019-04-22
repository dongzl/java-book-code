package com.java.book.self.overload;

import java.io.IOException;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 16:57
 */
public class Parent {

    public ParentBean test(Integer a, String b) throws IOException {
        return new ParentBean();
    }
}
