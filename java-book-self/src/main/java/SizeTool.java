import org.apache.lucene.util.RamUsageEstimator;

import java.lang.instrument.Instrumentation;
import java.util.Date;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/30 下午12:40
 */
public class SizeTool {
    
    private static Instrumentation instrumentation;
    
    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }
    
    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
    
    public static void main(String[] args) {
        SettVipBean bean = new SettVipBean("d", 1L, (byte)1, 1, new Date(), new Date(), "d");
        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long a = RamUsageEstimator.sizeOf(bean);
        
        //计算指定对象本身在堆空间的大小，单位字节
        long b = RamUsageEstimator.shallowSizeOf(bean);

        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String c = RamUsageEstimator.humanSizeOf(bean);
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
