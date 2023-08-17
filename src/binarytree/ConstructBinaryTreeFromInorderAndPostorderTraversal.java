package binarytree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/7/18 15:15
 * @Description: 106. 从中序与后序遍历序列构造二叉树
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /**
     * 思路：递归，在中序遍历中定位根节点，就可以知道左右子树的节点数目。
     * 后序遍历：[ [ 左子树的后序遍历结果 ], [ 右子树的后序遍历结果 ], 根节点 ]，中序遍历：[ [ 左子树的中序遍历结果 ], 根节点, [ 右子树的中序遍历结果 ] ]。
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 记录中序遍历中每个元素的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int iStart, int iEnd, int pStart, int pEnd, Map<Integer, Integer> map) {
        if (pStart > pEnd) {
            return null;
        }

        int rootVal = postorder[pEnd];
        int inorderRoot = map.get(rootVal);
        // 确定右子树的节点数量
        int rightSize = iEnd - inorderRoot;
        TreeNode node = new TreeNode(rootVal);
        // 右子树：中序：根节点到右边界，后序：右边界往前 rightSize 个元素
        node.right = buildTreeHelper(inorder, postorder, inorderRoot + 1, iEnd, pEnd - rightSize, pEnd - 1, map);
        // 左子树：中序：左边界到根节点的前一个位置，中序：左边界到右边界往前 rightSize + 1 个元素
        node.left = buildTreeHelper(inorder, postorder, iStart, inorderRoot - 1, pStart, pEnd - rightSize - 1, map);
        return node;
    }

    /**
     * 思路：用一个栈和一个指针辅助进行二叉树的构造。
     * 初始时栈中存放了根节点（后序遍历的最后一个节点），index 指针指向中序遍历的最后一个节点；
     * 依次枚举后序遍历中除了最后一个节点以外的每个节点。如果 index 恰好指向栈顶节点，接下来不断地弹出栈顶节点并向左移动 index，并将当前节点作为最后一个弹出的节点的左子节点；
     * 如果 index 和栈顶节点不同，将当前节点作为栈顶节点的右子节点；
     * 最后将当前节点入栈。
     * <p>
     * 栈中节点的顺序和它们在反向中序遍历中出现的顺序一定是相反的。例如：
     * inorder = [15, 9, 10, 3, 20, 5, 7, 8, 4]
     * postorder = [15, 10, 9, 5, 4, 8, 7, 20, 3]
     * 当遍历到 5 时，栈中的弹出序列为 4,8,7,20，反向中序遍历的序列为 4,8,7,15,20，说明 15 为 20 的左子节点。
     * 这是因为栈中的任意两个相邻的节点，前者都是后者的某个祖先。并且栈中的任意一个节点的左子节点还没有被遍历过，说明后者一定是前者右子节点的子树中的节点，那么后者就先于前者出现在反向中序遍历中。
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;

        for (int i = postorder.length - 2; i >= 0; i--) {
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorder[i]);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorder[i]);
                stack.push(node.left);
            }
        }
        return root;
    }

}
