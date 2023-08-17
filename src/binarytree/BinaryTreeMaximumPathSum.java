package binarytree;

/**
 * @Author: Druid
 * @Date: 2023/7/20 11:23
 * @Description: 124. 二叉树中的最大路径和
 */
public class BinaryTreeMaximumPathSum {

    private int maxSum = -1000;

    /**
     * 思路：深度优先遍历。计算二叉树中的一个节点的最大贡献值，空节点的最大贡献值等于 0；非空节点的最大贡献值等于节点值与其左右子树中的最大贡献值之和。
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);
        // 记录最大路径和
        maxSum = Math.max(maxSum, node.val + left + right);
        // 当前节点能为所在路径提供的最大值
        int subSum = node.val + Math.max(left, right);
        return Math.max(subSum, 0);
    }
}
