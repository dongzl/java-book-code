import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @author dongzonglei
 * @description
 * @date 2020/4/26 下午10:00
 */
public class TestTest {
    
    public static void main(String[] args) throws Exception {
        FileInputStream stream = new FileInputStream(new File("/data/ebook-admin/data.txt"));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        Long sum = 0L;
        while ((line = buffer.readLine()) != null) {
            String[] array = line.split(" -> ");
            System.out.println(array[1]);
            String[] array2 = array[1].split(", ");
            System.out.println(array2[1]);
            sum += Integer.valueOf(array2[1]);
//            FileWriter fileWritter = new FileWriter("/data/ebook-admin/result.txt",true);
//            fileWritter.write(array[1] + "\n");
//            fileWritter.close();
        }
        System.out.println(sum);
    }
}
