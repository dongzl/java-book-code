import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/25 下午4:58
 */
public class FourThread implements Runnable {
    
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    
    private static Lock lock = new ReentrantLock();
    
    private static Condition condition = lock.newCondition();
    
    //当前循环到的数组下标
    private static volatile int currentCount = 0;
    
    //打印方法实现
    private PrintFunction printFunction;
    
    //线程标志
    private int flag;
    
    public FourThread(int flag, PrintFunction printFunction) {
        this.flag = flag;
        this.printFunction = printFunction;
    }
    
    /**
     * 检查当前数组元素应该由哪个线程执行打印
     * @param n
     * @return
     */
    private int checkFlag(int n) {
        if (n % 15 == 0) {
            return 0;
        } else if (n % 5 == 0) {
            return 1;
        } else if (n % 3 == 0) {
            return 2;
        } else {
            return 3;
        }
    }
    
    @FunctionalInterface
    interface PrintFunction {
        void print(int n);
    }
    
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (currentCount < array.length && checkFlag(array[currentCount]) % 4 != flag) {
                    condition.await();
                }
                if (currentCount < array.length) {
                    printFunction.print(array[currentCount]);
                    currentCount++;
                    condition.signalAll();
                } else {
                    //数组循环完之后直接return
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //return之前一定要释放锁，锁未释放掉，线程不会停下，会发生内存泄漏
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new FourThread(0, (n) -> System.out.print("C"))).start();
        new Thread(new FourThread(1, (n) -> System.out.print("B"))).start();
        new Thread(new FourThread(2, (n) -> System.out.print("A"))).start();
        new Thread(new FourThread(3, (n) -> System.out.print(n))).start();
    }
}
