import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author dongzonglei
 * @description
 * @date 2019-06-18 15:39
 */
public class PriorityQueueTest {

    public static void main(String args[]) throws Exception {
        Queue<String> priorityQueue = new PriorityQueue(11);
        priorityQueue.add("1");
        priorityQueue.add("2");
        priorityQueue.add("3");
        priorityQueue.add("4");
        for (String s : priorityQueue) {
            System.out.println(s);
        }
    }
}
