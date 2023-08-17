package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/28 11:27
 * @Description: 637. 二叉树的层平均值
 */
public class AverageOfLevelsInBinaryTree {

    /**
     * 思路：广度优先遍历。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = size;
            double total = 0;
            while (i > 0) {
                TreeNode node = queue.poll();
                total += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                i--;
            }
            res.add(total / size);
        }
        return res;
    }

    /**
     * 思路：深度优先遍历。
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        List<Integer> sizeList = new ArrayList<>();
        List<Double> sumList = new ArrayList<>();
        dfs(root, 0, sizeList, sumList);
        List<Double> averages = new ArrayList<Double>();
        int size = sumList.size();
        for (int i = 0; i < size; i++) {
            averages.add(sumList.get(i) / sizeList.get(i));
        }
        return averages;
    }

    private void dfs(TreeNode root, int depth, List<Integer> sizeList, List<Double> sumList) {
        if (root == null) {
            return;
        }
        if (depth < sizeList.size()) {
            sizeList.set(depth, sizeList.get(depth) + 1);
            sumList.set(depth, sumList.get(depth) + root.val);
        } else {
            sizeList.add(1);
            sumList.add(1.0 * root.val);
        }
        dfs(root.left, depth + 1, sizeList, sumList);
        dfs(root.right, depth + 1, sizeList, sumList);
    }

}
