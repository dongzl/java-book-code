import java.util.HashMap;
import java.util.Map;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/13 下午7:04
 */
public class TestHashMap {
    
    public static void main(String[] args) {
        int h = -1803964904;
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(h >>> 16));
        System.out.println(Integer.toBinaryString(h ^ (h >>> 16)));
    }
}
