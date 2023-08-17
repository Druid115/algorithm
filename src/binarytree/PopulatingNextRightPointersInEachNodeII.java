package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/7/18 17:43
 * @Description: 117. 填充每个节点的下一个右侧节点指针 II
 */
public class PopulatingNextRightPointersInEachNodeII {

    /**
     * 思路：广度优先遍历，使用队列保存每层的节点。
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i > 0) {
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

    /**
     * 思路：广度优先遍历，移除队列。
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur != null) {
            Node dummyNode = new Node(-1);
            Node pre = dummyNode;
            // 遍历当前层的节点
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            // 遍历下一层
            cur = dummyNode.next;
        }
        return root;
    }
}
