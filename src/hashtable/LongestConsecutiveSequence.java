package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Druid
 * @Date: 2023/6/25 15:57
 * @Description: 128. 最长连续序列
 */
public class LongestConsecutiveSequence {

    /**
     * 思路：hash 表对数组进行去重，再次遍历数组，检查当前 num - 1 的数字是否在 hash 表中，若存在则跳过，减少循环次数。
     */
    public int longestConsecutive(int[] nums) {
        int longestLen = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curLen = 1;
                int currentNum = num;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    curLen++;
                }
                longestLen = Math.max(longestLen, curLen);
            }
        }
        return longestLen;
    }


    /**
     * 思路：利用 map 减小遍历次数，value 表示的是 num 所在的连续区间长度。
     * 遍历数组中的所有数字 num，当 num 是第一次出现时：
     * 1. 分别获取到左相邻数字 num - 1 的连续区间长度 left 和右相邻数字 num + 1 的连续区间长度 right；
     * 2. 计算得到当前的区间长度为 curLen = left + right + 1；
     * 3. 更新最长区间长度 ans 以及左右边界的区间长度。
     */
    public int longestConsecutive2(int[] nums) {
        // key 表示 num，value 表示 num 所在连续区间的长度
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int curLen = left + right + 1;
                ans = Math.max(ans, curLen);
                // 将 num 加入 map 中，表示已经遍历过该值。其对应的 value 可以为任意值。
                map.put(num, -1);
                // 更新当前连续区间左边界和右边界对应的区间长度
                map.put(num - left, curLen);
                map.put(num + right, curLen);
            }
        }
        return ans;
    }
}
