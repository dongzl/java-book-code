/**
 * @author dongzonglei
 * @description
 * @date 2020/5/12 下午7:45
 */
public class CrossPrinter {
    
    private static final Object o = new Object();
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized(o) {
                        for (int i = 1; i < 100; i += 2) {
                            System.out.println(i);
                            o.notifyAll();
                            o.wait();
                        }
                        o.notifyAll();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized(o) {
                        for (int i = 2; i <= 100; i += 2) {
                            System.out.println(i);
                            o.notifyAll();
                            o.wait();
                        }
                        o.notifyAll();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
