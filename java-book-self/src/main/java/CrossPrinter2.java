import java.util.HashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/12 下午7:45
 */
public class CrossPrinter2 {
    
    private static Thread t1, t2;
    
    public static void main(String[] args) {
        
        t1 = new Thread(new Runnable() {
            
            public void run() {
                for (int i = 1; i < 100; i += 2) {
                    System.out.println(i);
                    LockSupport.unpark(t2);
                    LockSupport.park(t1);
                }
            }
        }, "t1");
        
        t2 = new Thread(new Runnable() {
            
            public void run() {
                for (int i = 2; i <= 100; i += 2) {
                    LockSupport.park(t2);
                    System.out.println(i);
                    LockSupport.unpark(t1);
                }
            }
        }, "t2");
        
        t1.start();
        t2.start();
    }
}