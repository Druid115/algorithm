package binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/7/26 16:55
 * @Description: 173. 二叉搜索树迭代器
 */
public class BinarySearchTreeIterator {

    static class BSTIterator {

        private int idx = -1;

        private final List<Integer> list = new ArrayList<>();

        /**
         * 思路：提前使用中序遍历将二叉树中的节点保存在数组中。
         */
        public BSTIterator(TreeNode root) {
            inorderTraversal(root);
        }

        private void inorderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }

        public int next() {
            return list.get(++idx);
        }

        public boolean hasNext() {
            return idx < list.size() - 1;
        }
    }

    static class BSTIterator2 {

        private TreeNode cur;
        private final Deque<TreeNode> stack;

        /**
         * 思路：使用栈，栈中只保存当前节点及其所有的左节点。节点出栈时，将该节点的右节点和右节点的所有左节点入栈。
         * 这样空间复杂度为 O(h)，h 为树的高度，因为栈中只保留了左节点，栈中元素最多的时候，就是遍历到树的叶节点的时候。
         */
        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new LinkedList<TreeNode>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }
}
