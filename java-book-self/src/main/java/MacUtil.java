import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/9/14 11:39 上午
 */
public class MacUtil {
    public static String getMacAddr() {
        try {
            //首先获取想要查看的ip地址，这个地址唯一对应一个网卡信息
            InetAddress ip = InetAddress.getLocalHost();
            //根据ip地址获得对应的网卡信息
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
            //获取网卡的mac地址字节数组，这个字节数组的长度是6，读者可以自行断点查看
            byte[] macAddr = networkInterface.getHardwareAddress();
            //将字节数组转成16进制表示
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < macAddr.length; i++) {
                //将每个字节的值转为16进制数
                String byteToHex = Integer.toHexString(macAddr[i] & 0xff);
                sb.append(byteToHex);
                //使用-来区分每个字节的16进制数表示
                if (i != macAddr.length - 1) {
                    sb.append("-");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("get mac addr error", e);
        }
    }

    public static void main(String[] args) {
        String macAddr = getMacAddr();
        System.out.println(macAddr);
    }
}
