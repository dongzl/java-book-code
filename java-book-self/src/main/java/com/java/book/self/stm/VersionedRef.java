package com.java.book.self.stm;

/**
 * @author dongzonglei
 * @description 带版本号的对象引用
 * @date 2019-08-10 15:21
 */
public final class VersionedRef<T> {

    final T value;

    final long version;

    // 构造方法
    public VersionedRef(T value, long version) {
        this.value = value;
        this.version = version;
    }
}
