package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/6/15 16:01
 * @Description: 3. 无重复字符的最长子串
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 思路：使用滑动窗口，窗口内的字符都不重复，遇到重复字符时，更新左边界到窗口内重复字符的右边。在遍历的过程中更新最长的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = 0;
        while (right < charArray.length) {
            if (map.containsKey(charArray[right])) {
                // 注意，这里对左边界移动的位置做了判断，永远取最大的位置，避免了对移动区间内的字符的移除操作
                left = Math.max(left, map.get(charArray[right]) + 1);
            }
            res = Math.max(res, right - left + 1);
            map.put(charArray[right], right);
            right++;
        }
        return res;
    }
}
