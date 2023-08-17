package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/6/29 15:31
 * @Description: 224. 基本计算器
 */
public class BasicCalculator {

    /**
     * 思路：使用两个栈，一个记录数字，一个记录操作。
     */
    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        // 先加入一个 0，避免对第一个数可能是负数的单独判断
        nums.push(0);
        s = s.replace(" ", "");
        char[] arr = s.toCharArray();
        int idx = 0;
        while (idx < arr.length) {
            if (arr[idx] == '(') {
                ops.push(arr[idx]);
                idx++;
                continue;
            }

            if (arr[idx] == ')') {
                while (!ops.isEmpty()) {
                    char op = ops.pop();
                    if (op != '(') {
                        calcul(nums, op);
                    } else {
                        break;
                    }
                }
                idx++;
                continue;
            }

            if (arr[idx] == '-' || arr[idx] == '+') {
                // 判断前置位
                if (idx > 0 && (arr[idx - 1] == '(' || arr[idx - 1] == '+' || arr[idx - 1] == '-')) {
                    nums.push(0);
                }
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                while (!ops.isEmpty() && '(' != ops.peek()) {
                    calcul(nums, ops.pop());
                }
                ops.push(arr[idx]);
                idx++;
                continue;
            }

            // 找到完整的数字
            int num = 0;
            while (idx < arr.length && isValid(arr[idx])) {
                num = num * 10 + (arr[idx] - '0');
                idx++;
            }
            nums.push(num);
        }

        while (!ops.isEmpty()) {
            calcul(nums, ops.pop());
        }

        return nums.pop();
    }

    private boolean isValid(char c) {
        return c >= '0' && c <= '9';
    }

    private void calcul(Deque<Integer> nums, Character op) {
        Integer second = nums.pop();
        Integer first = nums.pop();

        if (op == '+') {
            nums.push(first + second);
        } else {
            nums.push(first - second);
        }
    }

    /**
     * 思路：使用一个栈。用一个变量 res 维护当前正在算的区块的和，若当前位置是位于 () 内的，res 就是 () 内的和；如果是 () 外的，就是当前位置左边的和。同时用一个变量 sign 维护当前操作符的正负。
     * 1. 遇到数字时，向右计算出完整的数字并追加到当前区块的和 res 中
     * 2. 遇到 "+" 或 "-" 时，将符号位记录在 sign 中
     * 3. 遇到 "(" 时，表明重新开始了一个区块。先将之前区块的 res 和 sign 进栈，然后 res 置 0，sign 置 1，开始重新计算新区块的值
     * 4. 遇到 ")" 时，标志着该区块结束，将当前 () 内区块的 res * sign 追加到栈顶保存的旧的 res 中。
     */
    public int calculate2(String s) {
        int res = 0;
        int sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        s = s.replace(" ", "");
        char[] array = s.toCharArray();
        int idx = 0;
        while (idx < array.length) {
            switch (array[idx]) {
                case '+':
                    sign = 1;
                    idx++;
                    break;
                case '-' :
                    sign = -1;
                    idx++;
                    break;
                case '(':
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                    idx++;
                    break;
                case ')':
                    // pop 的顺序跟上一个块中的 push 顺序对应，第一个是符号，第二个是之前区块的值
                    res = stack.pop() * res + stack.pop();
                    idx++;
                    break;
                default:
                    int num = 0;
                    while (idx < array.length && isValid(array[idx])) {
                        num = num * 10 + (array[idx] - '0');
                        idx++;
                    }
                    res += num * sign;
                    break;
            }
        }
        return res;
    }
}
