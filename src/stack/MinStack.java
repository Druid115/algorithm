package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * @Author: Druid
 * @Date: 2023/6/28 10:10
 * @Description: 155. 最小栈
 */
public class MinStack {


    static class MinStack1 {
        private Deque<Integer> stack;
        private Deque<Integer> minStack;

        /**
         * 思路：使用两个栈，一个存放输入的值，一个存放最小值
         */
        public MinStack1() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            // 注意栈顶元素不大于新添加的元素时，添加该元素，避免多次插入的元素均等于最小值的情况，例如 0，1，0
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
            stack.push(val);
        }

        public void pop() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            Integer pop = stack.pop();
            if (Objects.equals(pop, minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            return stack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            return minStack.peek();
        }
    }


    static class MinStack2 {

        private long min;
        private Deque<Long> stack;

        /**
         * 思路：使用一个栈，存放新增元素与当前最小值的差值。
         */
        public MinStack2() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                min = val;
                stack.push(val - min);
            } else {
                stack.push(val - min);
                // 如果差值小于 0，说明新增的元素更小
                if (val - min < 0) {
                    min = val;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            Long pop = stack.pop();
            // 栈顶元素为负，要更新 min
            if (pop < 0) {
                min = min - pop;
            }
        }

        public int top() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            long top = stack.peek();
            // 栈顶元素为负，说明插入元素为 min
            if (top < 0) {
                return (int) min;
            } else {
                return (int) (top + min);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    static class MinStack3 {
        private ListNode head;

        /**
         * 思路：自定义链表，在 node 节点中记录当前元素对应的 min 值。
         */
        public MinStack3() {
        }

        public void push(int val) {
            if (head != null) {
                head = new ListNode(val, Math.min(val, head.min), head);
            } else {
                head = new ListNode(val, val, head);
            }
        }

        public void pop() {
            if (head == null) {
                throw new IllegalStateException("栈为空...");
            }
            head = head.next;
        }

        public int top() {
            if (head == null) {
                throw new IllegalStateException("栈为空...");
            }
            return head.val;
        }

        public int getMin() {
            if (head == null) {
                throw new IllegalStateException("栈为空...");
            }
            return head.min;
        }

        static class ListNode {
            public int val;
            public int min;
            public ListNode next;

            public ListNode(int val, int min, ListNode next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }
}