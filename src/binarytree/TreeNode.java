package binarytree;

/**
 * @Author: Druid
 * @Date: 2023/7/6 14:45
 * @Description: 二叉树节点定义
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
