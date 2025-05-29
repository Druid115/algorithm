/**
 * @Author: DingRD
 * @Date: 2024/11/13 17:36
 * @Description:
 */
public class StringContains {

    public boolean contains(String s, String str) {
        char[] sCharArray = s.toCharArray();
        char[] charArray = str.toCharArray();

        if (sCharArray.length < charArray.length) {
            return false;
        }

        int i = 0;
        int j = 0;
        while (i < sCharArray.length) {
            int k = i;
            while (j < charArray.length && k < sCharArray.length) {
                if (charArray[j] == sCharArray[k]) {
                    k++;
                    j++;
                } else {
                    break;
                }
            }
            if (j == charArray.length) {
                return true;
            } else {
                j = 0;
                i++;
            }
        }
        return false;
    }


    class Node {
        public int val;

        public long time;

        public Node next;


        public Node(int val, long time) {

        }
    }


    class DelayQueue {

        private Node head;

        public DelayQueue() {
            head = new Node(-1, 0);
        }

        public void put(int val, long time) {
            Node node = head;
            while (node.next != null && node.next.time < time) {
                node = node.next;
            }
            Node newNode = new Node(val, time);
            newNode.next = node.next;
            node.next = newNode;
        }

        public int get() {
            while (head.next != null) {
                long cur = System.currentTimeMillis();
                if (cur <= head.next.time) {
                    int val = head.next.val;
                    head.next = head.next.next;
                    return val;
                }
            }
            return -1;
        }
    }

}
