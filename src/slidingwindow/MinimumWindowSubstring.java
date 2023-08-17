package slidingwindow;

/**
 * @Author: Druid
 * @Date: 2023/6/19 17:15
 * @Description: 76. 最小覆盖子串
 */
public class MinimumWindowSubstring {

    /**
     * 思路：使用滑动窗口，当窗口包含了字符串 t 的全部字符时，移动左边界，直到无法全部包含字符串 t 为止。更新窗口的最小长度及起始位置。
     */
    public String minWindow(String s, String t) {
        int[] arr = new int[128];
        char[] tCharArray = t.toCharArray();
        for (char c : tCharArray) {
            arr[c]++;
        }

        char[] sCharArray = s.toCharArray();
        // 起始位置
        int start = 0;
        // 窗口大小
        int size = sCharArray.length + 1;
        int left = 0;
        int right = 0;
        // 记录包含字符串 t 的字符个数
        int num = tCharArray.length;
        while (right < sCharArray.length) {
            if (arr[sCharArray[right]] > 0) {
                num--;
            }
            arr[sCharArray[right]]--;

            if (num == 0) {
                // 移动左边界，当 arr 数组中的元素次数为 0 的时候，意味着如果继续移动左边界，窗口将不再包含字符串 t 的全部字符了
                while (left < right && arr[sCharArray[left]] < 0) {
                    arr[sCharArray[left]]++;
                    left++;
                }

                if (right - left + 1 < size) {
                    start = left;
                    size = right - left + 1;
                }
            }
            right++;
        }
        return size == sCharArray.length + 1 ? "" : s.substring(start, size);
    }
}
