package binarytree;

/**
 * @Author: Druid
 * @Date: 2023/7/27 11:08
 * @Description: 222. 完全二叉树的节点个数
 */
public class CountCompleteTreeNodes {

    /**
     * 思路：对 root 节点的左右子树进行高度统计，分别记为 left 和 right。
     * 1. left == right。说明左子树一定是满二叉树，因为节点已经填充到右子树了。所以左子树的节点总数是 2^left - 1，加上当前的 root 节点，正好是 2^left，再对右子树进行递归统计。
     * 2. left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。再对左子树进行递归查找。
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            root = root.left;
            level++;
        }
        return level;
    }
}
