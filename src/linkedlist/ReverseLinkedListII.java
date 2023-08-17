package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/3 17:28
 * @Description: 92. 反转链表 II
 */
public class ReverseLinkedListII {

    /**
     * 思路：找到位于 left 位置的前一个节点，使用头插法依次翻转后面的节点，注意维护链表的连续性。
     * <p>
     * 相关题目：
     * {@link ReverseNodesInKGroup}
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        int count = 1;
        ListNode pre = dummyNode;
        while (count < left) {
            pre = pre.next;
            count++;
        }
        ListNode cur = pre.next;
        ListNode node;
        while (count < right) {
            node = cur.next;
            cur.next = node.next;
            node.next = pre.next;
            pre.next = node;
            count++;
        }
        return dummyNode.next;
    }
}
