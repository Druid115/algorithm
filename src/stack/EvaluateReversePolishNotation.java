package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/6/29 11:22
 * @Description: 150. 逆波兰表达式求值
 */
public class EvaluateReversePolishNotation {

    /**
     * 思路：栈。
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            int second;
            int first;
            switch (s) {
                case ("+"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first + second);
                    break;
                case ("-"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first - second);
                    break;
                case ("*"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first * second);
                    break;
                case ("/"):
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first / second);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }
}
