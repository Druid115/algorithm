package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/6/27 14:59
 * @Description: 71. 简化路径
 */
public class SimplifyPath {

    /**
     * 思路：队列。
     */
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> queue = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                queue.offerLast(name);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append("/").append(queue.pollFirst());
        }
        if (sb.length() == 0) {
            return "/";
        }
        return sb.toString();
    }
}
