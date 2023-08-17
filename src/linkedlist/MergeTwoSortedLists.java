package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/3 10:37
 * @Description: 21. 合并两个有序链表
 */
public class MergeTwoSortedLists {

    /**
     * 思路：遍历并比较链表节点的数值。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        node.next = list1 != null ? list1 : list2;

        return head.next;
    }

}
