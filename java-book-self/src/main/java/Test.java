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
}
