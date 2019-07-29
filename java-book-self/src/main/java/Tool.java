import java.text.SimpleDateFormat;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-29 19:50
 */
public class Tool {
    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread().getName());
        System.out.println(SafeDateFormat.get());
        System.out.println(System.identityHashCode(SafeDateFormat.get()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(SafeDateFormat.get());
                System.out.println(System.identityHashCode(SafeDateFormat.get()));
            }
        }).start();

    }

    static class SafeDateFormat{
        static final ThreadLocal<SimpleDateFormat> sdf =
                ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        static SimpleDateFormat get(){
            return sdf.get();
        }
    }
}
