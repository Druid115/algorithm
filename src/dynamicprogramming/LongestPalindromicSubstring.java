package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/6 15:45
 * @Description: 5. 最长回文子串
 */
public class LongestPalindromicSubstring {

    /**
     * 思路：动态规划。dp[i][j] 表示子串 s[i..j] 是否为回文子串，可以取到 s[i] 和 s[j]。dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]。
     * 如果 s[i..j] 的长度严格小于 4，即 j − 1 + 1 < 4 时，此时 s[i..j] 是否是回文只取决于 s[i] 与 s[j] 是否相等。
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    /**
     * 思路：中心扩散法。遍历每一个下标，以这个下标为中心，利用回文串中心对称的特点，往两边扩散，看最多能扩散多远。
     * 奇数回文串的中心是一个具体的字符，偶数回文串的中心是位于中间的两个字符的空隙。
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        // 最后一个位置无法向外扩散
        for (int i = 0; i < len - 1; i++) {
            int[] odd = centerSpread(s, i, i);
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }

}
