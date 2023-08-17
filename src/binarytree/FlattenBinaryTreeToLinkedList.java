package binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: Druid
 * @Date: 2023/7/18 18:46
 * @Description: 114. 二叉树展开为链表
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * 思路：展开某节点为链表，可转化为寻找当前节点的前驱节点：
     * 1. 如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点；
     * 2. 将当前节点的右子节点赋给前驱节点的右子节点；
     * 3. 当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。
     */
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode node = cur.left;
            if (node != null) {
                while (node.right != null) {
                    node = node.right;
                }
                node.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    TreeNode pre = null;

    /**
     * 思路：利用先序遍历的思想，每遍历一个节点，就将上一个节点的右指针更新为当前节点，但这样上一个节点的右指针就丢失了。
     * 因此，可以变形后序遍历，遍历顺序是右子树 -> 左子树 -> 根节点。
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    /**
     * 思路：方法二的遍历版本。
     */
    public void flatten3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            // 已经访问到最右的节点了
            cur = stack.peek();
            // 在不存在左节点或者右节点已经访问过的情况下，访问根节点
            if (cur.left == null || cur.left == pre) {
                stack.pop();
                cur.right = pre;
                cur.left = null;
                pre = cur;
                cur = null;
            } else {
                // 左节点还没有访问过就先访问左节点
                cur = cur.left;
            }
        }
    }

    /**
     * 思路：特殊的先序遍历，先将右子节点保存到栈中，可以防止右子节点的丢失。
     */
    public void flatten4(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (pre != null) {
                pre.right = node;
                pre.left = null;
            }
            pre = node;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
