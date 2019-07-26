package com.java.book.self.guarded;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 10:35
 */
public class Request {

    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
