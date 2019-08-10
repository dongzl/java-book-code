package com.java.book.self.stm;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-10 15:29
 */
@FunctionalInterface
public interface TxnRunnable {

    void run(Txn txn);
}
