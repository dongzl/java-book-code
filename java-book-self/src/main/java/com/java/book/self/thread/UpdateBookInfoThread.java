package com.java.book.self.thread;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-13 11:40
 */
public class UpdateBookInfoThread extends AbstractBookInfoThread {

    private Integer i;

    public UpdateBookInfoThread(Integer i) {
        this.i = i;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception {
        System.out.println("UpdateBookInfoThread=====" + i);
        Thread.sleep(10000);
        return Boolean.TRUE;
    }
}
