package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Druid
 * @Date: 2023/6/25 11:35
 * @Description: 202. 快乐数
 */
public class HappyNumber {

    /**
     * 思路：每一位数的最大数字经过计算后的下一位数不会超过 243。
     * 使用 hash 表记录每次计算后的数字，如果已经包含了该数字，说明陷入了循环，结果不可能为 1。
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * 思路：快慢指针。方法一中的反复调用 getNext(n) 得到的链是一个隐式的链表，因此可以使用快慢指针判断是否存在环。
     */
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(fast);
            fast = getNext(fast);
        } while (slow != fast);
        return slow == 1;
    }


    public int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int m = n % 10;
            total += m * m;
            n = n / 10;
        }
        return total;
    }
}
