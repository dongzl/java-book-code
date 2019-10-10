import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-13 20:24
 */
public class Test {

    public boolean isPalindrome(int x) {
        String a = String.valueOf(x);
        char[] b = a.toCharArray();
        char[] c = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            c[i] = b[b.length - 1 - i];
        }
        return String.valueOf(c).equals(a);
    }

    public static void main(String args[]) throws Exception {
        String temp = ".abc.def";
        String[] keyArray = temp.split("\\.");
        String a = ".abc.def.hij";
        String b = ".abc.def.hijk";
        String c = ".abc.def.hijk.l";
        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        for (String item : list) {
            String[] itemKeyArray = item.split("\\.");
            if (itemKeyArray.length < keyArray.length + 1) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < keyArray.length + 1; i++) {
                builder.append(itemKeyArray[i]);
                if (i < keyArray.length) {
                    builder.append(".");
                }
            }
            System.out.println(builder.toString());
        }
    }
}
