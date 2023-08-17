package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/20 10:31
 * @Description: 129. 求根节点到叶节点数字之和
 */
public class SumRootToLeafNumbers {

    /**
     * 思路：深度优先遍历。遍历每个节点，如果遇到叶子节点，则将叶子节点对应的数字加到数字之和。如果当前节点不是叶子节点，则计算其子节点对应的数字，然后对子节点递归遍历。
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }

    public int sumNumbersHelper(TreeNode node, int prevSum) {
        if (node == null) {
            return 0;
        }
        int sum = prevSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }
        return sumNumbersHelper(node.left, sum) + sumNumbersHelper(node.right, sum);
    }

    /**
     * 思路：广度优先遍历。维护两个队列，分别存储节点和节点对应的数字。
     */
    public int sumNumbers2(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int cur = valQueue.poll();
            if (node.left == null && node.right == null) {
                sum += cur;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(cur * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(cur * 10 + node.right.val);
            }
        }
        return sum;
    }
}
