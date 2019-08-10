package com.java.book.self.stm;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-10 15:36
 */
public class Account {

    private TxnRef<Integer> balance;

    public Account(Integer balance) {
        this.balance = new TxnRef<>(balance);
    }

    public void transfer(Account target, int amt) {
        STM.atomic((txn -> {
            Integer from = balance.getValue(txn);
            balance.setValue(from - amt, txn);
            Integer to = target.balance.getValue(txn);
            target.balance.setValue(to + amt, txn);
        }));
    }
}
