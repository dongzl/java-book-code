import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-26 09:48
 */
public class GuardedObjectV2<T> {

    // 受保护的对象
    T obj;

    final Lock lock = new ReentrantLock();

    final Condition done = lock.newCondition();

    final int timeout = 2;

    final static Map<Object, GuardedObjectV2> gos = new ConcurrentHashMap<>();

    static <K> GuardedObjectV2 create(K key) {
        GuardedObjectV2 go = new GuardedObjectV2();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObjectV2 go = gos.get(key);
        if (go != null) {
            go.onChange(obj);
        }
    }

    // 受保护的对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA 通过while循环防止虚假唤醒
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }

    // 改变对象状态，唤醒等待线程
    void onChange(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
