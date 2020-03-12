package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午3:32
 */
public class BlockingQueueDemo {
    
    static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);
    
    public static void main(String[] args) {
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String d : digit) {
                    System.out.println(d);
                    try {
                        queue1.put("ok");
                        queue2.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String a : alphabet) {
                    try {
                        queue1.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(a);
                    try {
                        queue2.put("ok");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();
    }
}
