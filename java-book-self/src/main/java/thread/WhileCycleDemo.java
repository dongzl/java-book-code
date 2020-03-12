package thread;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午2:49
 */
public class WhileCycleDemo {
    
    enum RunThreadEnum {T1, T2}
    
    private static volatile RunThreadEnum run = RunThreadEnum.T1;
    
    public static void main(String[] args) {
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String d : digit) {
                    while (run != RunThreadEnum.T1) {}
                    System.out.println(d);
                    run = RunThreadEnum.T2;
                }
            }
        }, "t1").start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String a : alphabet) {
                    while (run != RunThreadEnum.T2) {}
                    System.out.println(a);
                    run = RunThreadEnum.T1;
                }
            }
        }, "t2").start();
    }
}
