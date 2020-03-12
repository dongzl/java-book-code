package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午4:56
 */
public class LockConditionDemo {
    
    public static void main(String[] args) {
        
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (String d : digit) {
                        System.out.println(d);
                        condition2.signalAll();
                        condition1.await();
                    }
                    condition2.signalAll(); // 需要调用一次，否则程序无法终止
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t1").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (String a : alphabet) {
                        System.out.println(a);
                        condition1.signalAll();
                        condition2.await();
                    }
                    condition1.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t2").start();
    }
}
