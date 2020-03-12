import org.openjdk.jol.info.ClassLayout;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/12 下午8:35
 */
public class HelloJOL {
    
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
