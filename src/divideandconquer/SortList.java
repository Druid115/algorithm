package divideandconquer;

import linkedlist.ListNode;

/**
 * @Author: Druid
 * @Date: 2023/8/22 9:58
 * @Description: 148. 排序链表
 */
public class SortList {

    /**
     * 思路：通过递归实现链表的归并排序。找到当前链表中点，并从中点将链表断开，合并这两个断开的链表。
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 使用快慢指针定位中点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        node.next = left != null ? left : right;
        return dummyHead.next;
    }

    /**
     * 思路：使用迭代的方式实现归并排序。设定每次迭代的步长，每迭代一次，步长扩大一倍，直到步长 > 链表长度，代表已经排序完成。
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        for (int step = 1; step <= len; step *= 2) {
            ListNode tail = dummyNode;
            ListNode cur = dummyNode.next;

            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(cur, step);
                // 提前切割好下一段
                cur = cut(right, step);

                tail.next = merge(left, right);
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dummyNode.next;
    }

    private ListNode cut(ListNode node, int len) {
        while (--len > 0 && node != null) {
            node = node.next;
        }
        if (node == null) {
            return node;
        }
        ListNode next = node.next;
        node.next = null;
        return next;
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummyNode = new ListNode(-1);
        ListNode node = dummyNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        node.next = left != null ? left : right;
        return dummyNode.next;
    }
}
