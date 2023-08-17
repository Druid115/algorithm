package binarytree;

/**
 * @Author: Druid
 * @Date: 2023/7/31 10:13
 * @Description: 530. 二叉搜索树的最小绝对差
 */
public class MinimumAbsoluteDifferenceInBST {

    private int res = Integer.MAX_VALUE;

    private int pre = -1;

    /**
     * 思路：深度优先遍历。对二叉搜索树进行中序遍历，保证遍历过程中的有序性。
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre != -1) {
            res = Math.min(res, root.val - pre);
        }
        pre = root.val;
        dfs(root.right);
    }
}
