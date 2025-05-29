package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/7 15:26
 * @Description: 72. 编辑距离
 */
public class EditDistance {

    /**
     * 思路：动态规划。可以沿用 97. 交错字符串 的思想，转换为数组来进行思考。dp[i][j] 表示 word1 前 i 个字符转换成 word2 的前 j 个字符需要最少步数。
     * 当 word1[i] == word2[j]，dp[i][j] = dp[i - 1][j - 1]；
     * 否则，dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1。其中，dp[i - 1][j - 1] 表示替换操作，dp[i - 1][j] 表示删除操作，dp[i][j - 1] 表示插入操作。
     */
    public int minDistance(String word1, String word2) {
        int row = word1.length();
        int col = word2.length();
        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= row; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int j = 1; j <= col; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[row][col];
    }
}
