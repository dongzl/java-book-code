package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午2:22
 */
public class LockSupportDemo {
    
    private static Thread t1, t2;
    
    public static void main(String[] args) {
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String d : digit) {
                    System.out.println(d);
                    LockSupport.unpark(t2);
                    LockSupport.park(t1);
                }
            }
        }, "t1");
        
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String a : alphabet) {
                    LockSupport.park(t2);
                    System.out.println(a);
                    LockSupport.unpark(t1);
                }
            }
        }, "t2");
        
        t1.start();
        t2.start();
    }
}
