import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/23 下午2:01
 */
class Solution {
    
    class Node {
        private String res;
        private int left;
        private int right;
        public Node (String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(new Node("", n, n));
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (currentNode.left == 0 && currentNode.right == 0) {
                result.add(currentNode.res);
            }
            if (currentNode.right > currentNode.left) {
                stack.push(new Node(currentNode.res + ")", currentNode.left, currentNode.right - 1));
            }
            if (currentNode.left > 0) {
                stack.push(new Node(currentNode.res + "(", currentNode.left - 1, currentNode.right));
            }
        }
        return result;
    }
}
