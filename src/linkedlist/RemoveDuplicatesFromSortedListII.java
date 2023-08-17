package linkedlist;

/**
 * @Author: Druid
 * @Date: 2023/7/4 15:32
 * @Description: 82. 删除排序链表中的重复元素 II
 */
public class RemoveDuplicatesFromSortedListII {

    /**
     * 思路：设置一个 pre 节点，标识已经处理过的链表末尾。
     * 遍历链表，比较当前节点和下一节点的值，如果相等，则继续遍历直至不等，将当前节点设置成 pre 的下一个节点；如果不等，则设置 pre 为当前节点。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode node = pre.next;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                int val = node.val;
                while (node != null && node.val == val) {
                    node = node.next;
                }
                // 删除了重复的节点，此时还不能移动 pre 的位置，需要继续判断后续节点是否重复
                pre.next = node;
            } else {
                pre = node;
                node = node.next;
            }
        }
        return dummyNode.next;
    }
}
