package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/8/3 17:09
 * @Description: 98. 验证二叉搜索树
 */
public class ValidateBinarySearchTree {

    /**
     * 思路：递归，判断当前节点的值是否超出了上下限，如果没有，更新其左右节点对应的上下限并进行递归判断。
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTHelper(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }
        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }
        return isValidBSTHelper(node.left, minVal, node.val) && isValidBSTHelper(node.right, node.val, maxVal);
    }

    /**
     * 思路：中序遍历，判断当前节点的值是否大于中序遍历的前一个节点的值。
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }
}
