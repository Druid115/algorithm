package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/4 11:08
 * @Description: 25. K 个一组翻转链表
 */
public class ReverseNodesInKGroup {

    /**
     * 思路：先统计链表的节点数量，计算可以翻转的组数，再依次对每个组内的元素进行翻转操作。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int total = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            total++;
        }
        int group = total / k;
        int curGroup = 0;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (curGroup < group) {
            int idx = 1;
            ListNode cur = pre.next;
            ListNode next;
            while (idx < k) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
                idx++;
            }
            pre = cur;
            curGroup++;
        }
        return dummyNode.next;
    }
}
