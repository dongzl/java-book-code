package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午4:11
 */
public class WaitNotifyDemo {
    
    private static volatile boolean flag = false;
    
    public static void main(String[] args) {
        
        Object o = new Object();
    
        CountDownLatch latch = new CountDownLatch(1);
        
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                //latch.await(); //借助 CountDownLatch 工具解决先后顺序问题(需要处理异常)
                synchronized (o) {
                    while (!flag) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (String d : digit) {
                        System.out.println(d);
                        try {
                            o.notifyAll();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notifyAll(); //一定再调用一次，否则程序无法退出执行
                }
            }
        }, "t1").start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (String a : alphabet) {
                        System.out.println(a);
                        //latch.countDown();
                        flag = true;
                        try {
                            o.notifyAll();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notifyAll();
                }
            }
        }, "t2").start();
    }
}
