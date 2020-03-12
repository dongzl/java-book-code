package thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/7 下午3:46
 */
public class PipedStreamDemo {
    
    public static void main(String[] args) throws Exception {
        String[] digit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();
        
        input1.connect(output2);
        input2.connect(output1);
        
        String msg = "exchange";
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[8];
                try {
                    for (String d : digit) {
                        System.out.println(d);
                        output1.write(msg.getBytes());
                        input1.read(buffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[8];
                try {
                    for (String a : alphabet) {
                        input2.read(buffer);
                        System.out.println(a);
                        output2.write(msg.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
