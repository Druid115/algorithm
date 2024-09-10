package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/11/24 10:58
 * @Description: 70. 爬楼梯
 */
public class ClimbingStairs {

    /**
     * 思路：动态规划。
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
