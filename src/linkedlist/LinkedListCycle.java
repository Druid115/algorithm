package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/6/30 11:30
 * @Description: 141. 环形链表
 */
public class LinkedListCycle {

    /**
     * 思路：快慢指针，快速指针一定会追上慢速指针。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
