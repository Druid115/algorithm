package divideandconquer;

import binarytree.TreeNode;

/**
 * @Author: Druid
 * @Date: 2023/8/21 10:19
 * @Description: 108. 将有序数组转换为二叉搜索树
 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * 思路：选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差 1。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);
        return node;
    }
}
