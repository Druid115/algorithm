package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/3 10:21
 * @Description: 2. 两数相加
 */
public class AddTwoNumbers {

    /**
     * 思路：遍历链表，处理好进位。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode dummyHead = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null? 0 : l2.val;
            int num = num1 + num2 + carry;

            head.next = new ListNode(num % 10);
            head = head.next;
            carry = num / 10;

            l1 = l1 == null? null : l1.next;
            l2 = l2 == null? null : l2.next;
        }

        if (carry > 0) {
            head.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
