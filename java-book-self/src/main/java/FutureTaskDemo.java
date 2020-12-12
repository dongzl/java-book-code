import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/21 下午5:49
 */
public class FutureTaskDemo {
    
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Task());
        executorService.submit(futureTask);
        System.out.println(futureTask.get(1, TimeUnit.SECONDS));
        FutureTask<String> futureTask2 = new FutureTask(new Task2(), "执行失败");
        executorService.submit(futureTask2);
        System.out.println(futureTask2.get(200, TimeUnit.MICROSECONDS));
    }
    
    static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            return "执行成功";
        }
    }
    
    static class Task2 implements Runnable {
        
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


