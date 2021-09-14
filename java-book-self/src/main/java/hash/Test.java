package hash;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/8/14 2:49 下午
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //System.out.println(new Object());
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("hashCode"));
        System.out.println(System.getProperties());
        Object o = new Object();
        int hashcode = o.hashCode();
        // toString
        System.out.println(o);
        // hashcode 十六进制
        System.out.println(Integer.toHexString(hashcode));
        // hashcode
        System.out.println(hashcode);
        // 这个方法，也是获取对象的 hashcode；不过和 Object.hashcode 不同的是，该方法会无视重写的hashcode
        System.out.println(System.identityHashCode(o));
        System.out.println(System.identityHashCode(new Object()));
        System.out.println(System.identityHashCode(new Object()));
        System.out.println(System.identityHashCode(new Object()));
        System.out.println(System.identityHashCode(new Object()));
    }
}
