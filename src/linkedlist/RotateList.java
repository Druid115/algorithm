package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/5 14:02
 * @Description: 61. 旋转链表
 */
public class RotateList {

    /**
     * 思路：设链表的长度为 n，当 k > n 时，只需要向右移动 k mod n 次即可。新链表最后一个节点为原链表的第 n - (k mod n) 个节点，倒数第 k mod n - 1 个节点。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        int step = k % length;
        if (step == 0) {
            return head;
        }

        // 使用快慢指针定位到最后一个节点
        ListNode slow = head;
        ListNode fast = head;
        while (step > 0) {
            fast = fast.next;
            step--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    /**
     * 思路：封闭成环，组成环后找到原链表的第 n - (k mod n) 个节点。
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode node = head;
        while (node.next != null) {
            length++;
            node = node.next;
        }

        int step = k % length;
        if (step == 0) {
            return head;
        }

        step = length - step;
        node.next = head;
        while (step > 0) {
            node = node.next;
            step--;
        }
        ListNode newHead = node.next;
        node.next = null;
        return newHead;
    }
}
