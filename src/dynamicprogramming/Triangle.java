package dynamicprogramming;

import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/12/5 16:37
 * @Description: 120. 三角形最小路径和
 */
public class Triangle {

    /**
     * 思路：动态规划。从上至下。
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len][len];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < len; i++) {
            List<Integer> list = triangle.get(i);
            dp[i][0] = dp[i - 1][0] + list.get(0);
            dp[i][i] = dp[i - 1][i - 1] + list.get(i);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + list.get(j);
            }
        }

        int res = dp[len - 1][0];
        for (int k = 1; k < len; k++) {
            if (dp[len - 1][k] < res) {
                res = dp[len - 1][k];
            }
        }
        return res;
    }

    /**
     * 思路：动态规划。从下至上。
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len + 1][len + 1];
        // 从三角形的最后一行开始递推
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
