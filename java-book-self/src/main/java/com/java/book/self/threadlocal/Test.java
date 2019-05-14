package com.java.book.self.threadlocal;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-14 15:22
 */
public class Test {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public static void main(String args[]) throws Exception {
//        final Test test = new Test();
//        test.set();
//
//        System.out.println(test.getLong());
//        System.out.println(test.getString());
//
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                test.set();
//                System.out.println(test.getLong());
//                System.out.println(test.getString());
//            }
//        };
//
//        thread1.start();
//        thread1.join();
//
//        System.out.println(test.getLong());
//        System.out.println(test.getString());

        final ThreadLocal threadLocal=new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return "dongzonglei";
            }
        };

        threadLocal.set("dongzonglei_test");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());//NULL
            }
        }).start();
    }
}
