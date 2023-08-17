package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/6 14:42
 * @Description: 104. 二叉树的最大深度
 */
public class MaximumDepthOfBinaryTree {

    /**
     * 思路：深度优先搜索，递归计算出当前节点的左子树和右子树的最大深度。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 思路：广度优先搜索，使用队列存放当前层的所有节点，再依次出队并存放下一层节点，直到队列为空，操作次数即为层数。
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }
}
