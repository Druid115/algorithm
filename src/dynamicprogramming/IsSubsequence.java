package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/6/9 16:07
 * @Description: 392. 判断子序列
 */
public class IsSubsequence {

    /**
     * 思路：动态规划。对于 t 的每一个位置，记录从该位置开始往后每一个字符第一次出现的位置。从后往前进行动态规划。
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[n + 1][26];
        // 设定边界状态，如果 dp[i][j] 为 n，说明从位置 i 开始往后不存在字符 j
        for (int i = 0; i < 26; i++) {
            dp[n][i] = n;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                dp[i][j] = dp[i + 1][j];
            }
            dp[i][t.charAt(i) - 'a'] = i;
        }

        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (dp[idx][s.charAt(i) - 'a'] == n) {
                return false;
            }
            // 每次更新 idx 后需要加一，才是新一轮的对比
            idx = dp[idx][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    /**
     * 思路：双指针。
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
