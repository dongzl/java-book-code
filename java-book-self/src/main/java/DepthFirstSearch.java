import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/23 下午12:50
 */
public class DepthFirstSearch {
    
    public List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 弹出栈顶元素
            TreeNode currentNode = stack.pop();
            result.add(currentNode.val);
            if (currentNode.right != null) {
                // 深度优先遍历，先遍历左边，后遍历右边，栈先进后出
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
        return result;
    }
    
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val= val;
        }
    }
}
