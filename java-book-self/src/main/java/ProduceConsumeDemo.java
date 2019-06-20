import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-18 16:34
 */
public class ProduceConsumeDemo {

    public static void main(String args[]) throws Exception {
        ArrayBlockingQueue<Cookie> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        Produce produce = new Produce(arrayBlockingQueue);
        new Thread(produce).start();
        //一个生产者，5个消费者
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
    }
}

class Produce implements Runnable {

    private static int i = 0;

    private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

    public Produce(ArrayBlockingQueue<Cookie> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (i < 1000) {
                arrayBlockingQueue.put(new Cookie("cookie" + i));
                if (++i % 100 == 0){//每生产100个，休息10s
                    Thread.sleep(10000);
                }
            }
        } catch (Exception e) {
            System.out.println("produce queue InterruptedException");
        }
    }
}

class Consume implements Runnable {

    private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

    public Consume(ArrayBlockingQueue<Cookie> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cookie poll = arrayBlockingQueue.poll(5, TimeUnit.SECONDS);//如果queue为null，那么5秒之后再去队列中取数据
                if (poll != null) {
                    System.out.println(Thread.currentThread().getName() + "--consume --" + poll);
                }
            }
        } catch (Exception e) {
            System.out.println("consume queue InterruptedException");
        }
    }
}

class Cookie {

    private String number;

    public Cookie(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "number='" + number + '\'' +
                '}';
    }
}
