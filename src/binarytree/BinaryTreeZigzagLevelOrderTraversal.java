package binarytree;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/7/28 15:10
 * @Description: 103. 二叉树的锯齿形层序遍历
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 思路：广度优先遍历。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrderLeft = true;
        while (!queue.isEmpty()) {
            // 使用双端队列，控制入队的方向
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = queue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }
}
