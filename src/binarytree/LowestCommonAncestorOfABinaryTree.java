package binarytree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Druid
 * @Date: 2023/7/27 15:53
 * @Description: 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     * 思路：通过递归对二叉树进行先序遍历，当遇到节点 p 或 q 时返回。递归左子节点，返回值记为 left；递归右子节点，返回值记为 right
     * 1. 当 left 和 right 同时为空，说明 root 的左 / 右子树中都不包含 p，q，返回 null；
     * 2. 当 left 和 right 同时不为空，说明 p，q 分列在 root 的 异侧，因此 root 为最近公共祖先，返回 root；
     * 3. 当 left 为空，right 不为空，说明 p，q 都不在 root 的左子树中，直接返回 right；
     * 4. 当 left 不为空，right 为空，说明 p，q 都不在 root 的右子树中，直接返回 left。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 1
        if (left == null && right == null) {
            return null;
        }
        // 3
        if (left == null) {
            return right;
        }
        // 4
        if (right == null) {
            return left;
        }
        // 2
        return root;
    }

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    /**
     * 思路：用哈希表存储所有节点的父节点，然后从 p 结点开始不断往上寻找，并记录已经访问过的节点，再从 q 节点开始不断往上寻找，如果碰到已经访问过的节点，那么这个节点就是最近公共祖先。
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
