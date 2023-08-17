package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/5/17 17:31
 * @Description: 151. 反转字符串中的单词
 */
public class ReverseWordsInAString {

    /**
     * 思路：双指针，从后往前遍历字符串，用两个指针标识每个单词的起始位置和终止位置，进行截取。
     */
    public String reverseWords(String s) {
        // 统一情况
        s = ' ' + s;
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        for (int left = n - 1, right = n; left >= 0; left--) {
            char ch = s.charAt(left);
            if (ch == ' ') {
                if (left + 1 < right) {
                    ans.append(s, left + 1, right).append(' ');
                }
                right = left;
            }
        }
        // 删除末尾多余的空格
        return ans.substring(0, ans.length() - 1);
    }
}
