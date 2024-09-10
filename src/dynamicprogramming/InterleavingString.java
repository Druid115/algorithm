package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/7 11:19
 * @Description: 97. 交错字符串
 */
public class InterleavingString {

    /**
     * 思路：动态规划。以 s1、s2 字符下标构建二维数组，问题可转化为从数组左上角开始，每次只能向右或者向下选择字符，
     * 是否存在一条到右下角的路径，该路径上所有字符组合正好为 s3。dp[i][j] 表示 s1 前 i 个字符与 s2 前 j 个字符能否拼接成 s3 的 i + j 个字符。
     * dp[i][j] = (dp[i - 1][j] && s3[i + j - 1] == s1[i - 1]) || (dp[i][j - 1] && s3[i + j - 1] == s2[j - 1])
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int row = s1.length();
        int col = s2.length();
        if (s3.length() != row + col) {
            return false;
        }
        boolean[][] dp = new boolean[row + 1][col + 1];
        dp[0][0] = true;
        // 处理边界条件
        for (int i = 1; i <= row && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j <= col && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[row][col];
    }
}
