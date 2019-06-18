import java.util.ArrayDeque;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-09 22:05
 */
public class ArrayDequeTest {

    public static void main(String args[]) throws Exception {
        ArrayDeque<String> deque = new ArrayDeque();
        deque.add("a");
        deque.add("b");
        deque.add("c");
        deque.add("d");
        deque.removeFirstOccurrence("c");
//        deque.addFirst(null);
//        System.out.println(deque.offerFirst(null));
        System.out.println(deque.toString());
    }
}