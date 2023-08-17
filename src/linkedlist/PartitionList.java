package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/5 18:03
 * @Description: 86. 分隔链表
 */
public class PartitionList {

    /**
     * 思路：维护两个链表 small 和 large，small 链表按顺序存储所有小于 x 的节点，large 链表按顺序存储所有大于等于  x 的节点。
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(-1);
        ListNode smallNode = smallHead;
        ListNode largeHead = new ListNode(-1);
        ListNode largeNode = largeHead;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                smallNode.next = node;
                smallNode = smallNode.next;
            } else {
                largeNode.next = node;
                largeNode = largeNode.next;
            }
            node = node.next;
        }
        smallNode.next = largeHead.next;
        largeNode.next = null;
        return smallHead.next;
    }
}
