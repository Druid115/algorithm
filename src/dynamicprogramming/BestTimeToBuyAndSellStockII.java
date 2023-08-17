package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/5/9 11:21
 * @Description: 122. 买卖股票的最佳时机 II
 */
public class BestTimeToBuyAndSellStockII {

    /**
     * 思路：动态规划。
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][0];
    }
}
