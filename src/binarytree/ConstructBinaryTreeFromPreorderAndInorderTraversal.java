package binarytree;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/7/17 17:04
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * 思路：递归，在中序遍历中定位根节点，就可以知道左右子树的节点数目。
     * 前序遍历：[ 根节点, [ 左子树的前序遍历结果 ], [ 右子树的前序遍历结果 ] ]，中序遍历：[ [ 左子树的中序遍历结果 ], 根节点, [ 右子树的中序遍历结果 ] ]。
     * <p>
     * 相关题目：
     * {@link ConstructBinaryTreeFromInorderAndPostorderTraversal}
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 记录中序遍历中每个元素的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int pStart, int pEnd, int iStart, int iEnd, Map<Integer, Integer> map) {
        if (pStart > pEnd) {
            return null;
        }

        int rootVal = preorder[pStart];
        // 在中序遍历中找到根节点的位置
        int inorderRoot = map.get(rootVal);
        // 确定左子树的节点数量
        int leftSubTreeSize = inorderRoot - iStart;
        TreeNode node = new TreeNode(rootVal);
        // 左子树：前序：左边界 + 1 开始的 leftSubTreeSize 个元素，中序：左边界到根节点的前一个位置
        node.left = buildTreeHelper(preorder, inorder, pStart + 1, pStart + leftSubTreeSize, iStart, inorderRoot - 1, map);
        // 右子树：前序：左边界 + 1 + leftSubTreeSize 开始到右边界，中序：根节点后一个位置到右边界
        node.right = buildTreeHelper(preorder, inorder, pStart + leftSubTreeSize + 1, pEnd, inorderRoot + 1, iEnd, map);
        return node;
    }

    /**
     * 思路：用一个栈和一个指针辅助进行二叉树的构造。
     * 初始时栈中存放了根节点（前序遍历的第一个节点），index 指针指向中序遍历的第一个节点；
     * 依次枚举前序遍历中除了第一个节点以外的每个节点。如果 index 恰好指向栈顶节点，接下来不断地弹出栈顶节点并向右移动 index，并将当前节点作为最后一个弹出的节点的右子节点；
     * 如果 index 和栈顶节点不同，将当前节点作为栈顶节点的左子节点；
     * 最后将当前节点入栈。
     * <p>
     * 栈中节点的顺序和它们在中序遍历中出现的顺序一定是相反的。例如：
     * preorder = [3, 9, 8, 5, 4, 10, 20, 15, 7]
     * inorder = [4, 5, 8, 10, 9, 3, 15, 20, 7]
     * 当遍历到 10 时，栈中的弹出序列为 4,5,8,9，中序遍历的序列为 4,5,8,10，说明 10 为 9 的右子节点。
     * 这是因为栈中的任意两个相邻的节点，前者都是后者的某个祖先。并且栈中的任意一个节点的右子节点还没有被遍历过，说明后者一定是前者左子节点的子树中的节点，那么后者就先于前者出现在中序遍历中。
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;

        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }
        return root;
    }
}
