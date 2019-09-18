/**
 * @author dongzonglei
 * @description
 * @date 2019-09-18 18:35
 */
public class TestGC {

    public static void main(String args[]) throws Exception {

        //-Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
        //查看 GC 信息
        System.out.println("max memory" + Runtime.getRuntime().maxMemory()); // 最大内存
        System.out.println("free memory" + Runtime.getRuntime().freeMemory()); // 空闲内存
        System.out.println("total memory" + Runtime.getRuntime().totalMemory()); // 总共使用的内存

        System.out.println("==========================================================");

        byte[] b1 = new byte[1 * 1024 * 1024];
        System.out.println("max memory" + Runtime.getRuntime().maxMemory());//最大内存
        System.out.println("free memory" + Runtime.getRuntime().freeMemory());//空闲内存
        System.out.println("total memory" + Runtime.getRuntime().totalMemory());//总共使用的内存

        System.out.println("==========================================================");

        byte[] b2 = new byte[4 * 1024 * 1024];
        System.out.println("max memory" + Runtime.getRuntime().maxMemory());//最大内存
        System.out.println("free memory" + Runtime.getRuntime().freeMemory());//空闲内存
        System.out.println("total memory" + Runtime.getRuntime().totalMemory());//总共使用的内存
    }
}
