package settlement;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongzonglei
 * @description
 * @date 2020/11/24 上午11:21
 */
public class MergeTest {

    public static void main(String[] args) throws Exception {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (int i = 5; i < 11; i++) {
            File file = new File("/source_code/GitHub/java-book-code/java-book-self/src/main/java/settlement/" + i + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] array = str.split("\t");
                Map<String, Integer> temp = map.getOrDefault(array[0], new HashMap<>());
                temp.put(array[1], NumberUtils.createInteger(array[2].trim()));
                map.put(array[0], temp);
            }
        }
        System.out.println(map);
    }
}
