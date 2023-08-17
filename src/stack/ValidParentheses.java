package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/6/27 11:35
 * @Description: 20. 有效的括号
 */
public class ValidParentheses {

    /**
     * 思路：栈。
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case ('('):
                case ('{'):
                case ('['):
                    stack.push(s.charAt(i));
                    break;
                case (')'):
                    if (stack.isEmpty() || '(' != stack.peek()) {
                        return false;
                    }
                    stack.pop();
                    break;
                case (']'):
                    if (stack.isEmpty() || '[' != stack.peek()) {
                        return false;
                    }
                    stack.pop();
                    break;
                case ('}'):
                    if (stack.isEmpty() || '{' != stack.peek()) {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
            }
        }
        return stack.isEmpty();
    }
}
