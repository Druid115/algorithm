package binarytree;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/7/31 11:20
 * @Description: 230. 二叉搜索树中第K小的元素
 */
public class KthSmallestElementInABST {

    private int k;
    private int ans;

    /**
     * 思路：中序遍历，遍历到的第 k 个节点即为所求。
     */
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null || k < 0) {
            return;
        }
        dfs(root.left);
        if (--k == 0) {
            ans = root.val;
        }
        dfs(root.right);
    }

    /**
     * 思路：迭代版的中序遍历。
     */
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.offer(root);
                root = root.left;
            }
            root = queue.pollLast();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }
}
