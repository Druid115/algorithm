package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/28 14:33
 * @Description: 102. 二叉树的层序遍历
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * 思路：广度优先遍历。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            res.add(curLevel);
        }
        return res;
    }

    /**
     * 思路：深度优先遍历。
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (depth < res.size()) {
            List<Integer> list = res.get(depth);
            list.add(root.val);
            res.set(depth, list);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
        }
        dfs(root.left, depth + 1, res);
        dfs(root.right, depth + 1, res);
    }

}
