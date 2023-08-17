package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/4 15:04
 * @Description: 19. 删除链表的倒数第 N 个结点
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * 思路：快慢指针，快指针领先慢指针 n - 1 个节点。之后继续遍历，当快指针遍历到链表末尾时，慢指针恰好处于倒数第 n 个节点的前一个节点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // 判断是否删除第一个节点
        if (fast == null) {
            return slow.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
