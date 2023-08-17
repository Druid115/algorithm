package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/28 10:59
 * @Description: 199. 二叉树的右视图
 */
public class BinaryTreeRightSideView {

    /**
     * 思路：广度优先遍历，记录下每层的最后一个元素。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (size == 1) {
                    res.add(node.val);
                }
                size--;
            }
        }
        return res;
    }

    /**
     * 思路：深度优先遍历，按照根结点 -> 右子树 -> 左子树的顺序访问，可以保证每层最先访问最右边的节点。
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        levelOrderRecursive(root, 0, res);
        return res;
    }

    public void levelOrderRecursive(TreeNode root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 如果当前节点所在深度还没有出现在 res 里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入 res 中。
        if (depth == res.size()) {
            res.add(root.val);
        }
        levelOrderRecursive(root.right, depth + 1, res);
        levelOrderRecursive(root.left, depth + 1, res);
    }
}
