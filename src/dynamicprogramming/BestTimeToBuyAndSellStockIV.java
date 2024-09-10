package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/20 14:41
 * @Description: 188. 买卖股票的最佳时机 IV
 */
public class BestTimeToBuyAndSellStockIV {

    /**
     * 思路：动态规划。分析同 123. 买卖股票的最佳时机 III。
     */
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        // 超过最大可交易次数，最大收益就不再取决于允许的最大交易次数，而是取决于股票价格数组的长度
        if (k >= length / 2) {
            return maxProfit(prices);
        }
        int[][][] dp = new int[length][k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < length; i++) {
            for (int j = k; j > 0; j--) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[length - 1][k][0];
    }

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
