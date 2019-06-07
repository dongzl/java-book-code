import java.util.TreeMap;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-05 22:08
 */
public class TreeMapTest {

    public static void main(String args[]) throws Exception {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("1", "语文");
        map.put("6", "数学");
        map.put("3", "物理");
        map.put("4", "化学");
        map.put("5", "历史");
        System.out.println(map.pollFirstEntry());
        System.out.println(map.size());
    }
}
