package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/7/3 10:46
 * @Description: 138. 复制带随机指针的链表
 */
public class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 思路：使用 map 映射原节点和新节点，然后以此将新节点的 next 和 random 指针补全。
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            Node newNode = map.get(node);
            newNode.next = map.get(node.next);
            newNode.random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    /**
     * 思路：构造 old1 -> new1 -> old2 -> new2 ... 这样的链表，然后设置新节点的 random 指针，最后拆分两个链表即可。
     */
    public Node copyRandomList1(Node head) {
        Node node = head;
        while (node != null) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }

        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        node = head;
        Node dummyHead = new Node(-1);
        Node newHead = dummyHead;
        while (node != null) {
            newHead.next = node.next;
            newHead = newHead.next;
            node.next = newHead.next;
            node = node.next;
        }
        return dummyHead.next;
    }
}
