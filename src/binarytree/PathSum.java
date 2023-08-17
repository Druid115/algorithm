package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/19 15:28
 * @Description: 112. 路径总和
 */
public class PathSum {

    /**
     * 思路：深度优先遍历。一直向下找到叶子节点，如果找到叶子节点时 sum == 0，说明找到了一条符合要求的路径。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 思路：广度优先遍历。使用两个队列分别存储将要遍历的节点，以及根节点到这些节点的路径和。
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer sum = valQueue.poll();
            if (node.left == null && node.right == null && sum == targetSum) {
                return true;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(sum + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(sum + node.right.val);
            }
        }
        return false;
    }
}
