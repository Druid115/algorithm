package divideandconquer;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/8/31 11:45
 * @Description: 23. 合并 K 个升序链表
 */
public class MergeKSortedLists {

    /**
     * 思路：用分治的方法对链表进行合并。将 k 个链表配对并将同一对中的链表合并，这样 k 个链表被合并成了 k / 2 个链表，
     * 然后是 k / 4 个链表， k / 8 个链表等等，重复这一过程，直到得到最终的有序链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        return mergeTwoLists(merge(lists, low, mid), merge(lists, mid + 1, high));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummyNode = new ListNode(-1);
        ListNode node = dummyNode;
        while (a != null && b != null) {
            if (a.val < b.val) {
                node.next = a;
                a = a.next;
            } else {
                node.next = b;
                b = b.next;
            }
            node = node.next;
        }
        node.next = a != null ? a : b;
        return dummyNode.next;
    }

    /**
     * 思路：使用优先队列合并。
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }
        return dummyHead.next;
    }
}
