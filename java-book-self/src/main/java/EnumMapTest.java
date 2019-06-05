import java.util.EnumMap;
import java.util.Map;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-04 18:28
 */
public class EnumMapTest {

    public static void main(String args[]) throws Exception {
        Map<Course, String> enumMap = new EnumMap<Course, String>(Course.class);
        enumMap.put(Course.THREE, "英语");
        enumMap.put(Course.ONE, "语文");
        enumMap.put(Course.ONE, "数学");
        enumMap.put(Course.TWO, "数学");
        for(Map.Entry<Course, String> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public enum Course {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }
}
