package thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午3:16
 */
public class AtomicBooleanDemo {
    
    static AtomicBoolean run = new AtomicBoolean(false);
    
    public static void main(String[] args) {
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String d : digit) {
                    while (run.get()) {}
                    System.out.println(d);
                    run.set(true);
                }
            }
        }, "t1").start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String a : alphabet) {
                    while (!run.get()) {}
                    System.out.println(a);
                    run.set(false);
                }
            }
        }, "t2").start();
    }
}
