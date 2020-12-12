import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/23 下午12:50
 */
public class DepthFirstSearchV2 {
    
    public List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }
    
    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        helper(node.left, result);
        helper(node.right, result);
    }
    
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val= val;
        }
    }
}
