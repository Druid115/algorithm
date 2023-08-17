package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/6 15:24
 * @Description: 100. 相同的树
 */
public class SameTree {

    /**
     * 思路：深度优先搜索，如果两个二叉树都不为空，首先判断它们的根节点的值是否相同，若不相同则返回不同；若相同，再分别判断根节点的左子树以及右子树是否相同。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * 思路：广度优先搜索，使用两个队列分别存储两个二叉树的节点。
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val) {
                return false;
            }
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;

            if (left1 == null ^ left2 == null) {
                return false;
            }
            if (right1 == null ^ right2 == null) {
                return false;
            }

            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right2 != null) {
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
