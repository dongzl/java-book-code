package com.java.book.self.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-13 11:38
 */
public class InvokeTest {

    public static void main(String args[]) throws Exception {
        List<Callable<Boolean>> threadList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            AbstractBookInfoThread createThread = new CreateBookInfoThread(i);
            AbstractBookInfoThread updateThread = new UpdateBookInfoThread(i);
            threadList.add(createThread);
            threadList.add(updateThread);
//            ThreadPoolUtils.submit(createThread);
//            ThreadPoolUtils.submit(updateThread);
        }
        ThreadPoolUtils.invokeAll(threadList);
        System.out.println("end");
    }
}
