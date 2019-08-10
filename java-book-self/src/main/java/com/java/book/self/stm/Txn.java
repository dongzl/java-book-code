package com.java.book.self.stm;

/**
 * @author dongzonglei
 * @description 事务接口
 * @date 2019-08-10 15:23
 */
public interface Txn {

    <T> T get(TxnRef<T> ref);

    <T> void set(TxnRef<T> ref, T value);
}
