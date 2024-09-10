package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/7 16:10
 * @Description: 123. 买卖股票的最佳时机 III
 */
public class BestTimeToBuyAndSellStockIII {

    /**
     * 思路：动态规划。dp[i][k] 表示在第 i 天结束时，最多进行 k 次交易的情况下可以获得的最大收益。
     * 只有买入操作会改变允许的最大交易次数，否则会超出最大交易次数限制。
     * dp[i][k][0] 表示持有 0 份股票的情况下可以获得的最大收益；
     * dp[i][k][1] 表示持有 1 份股票的情况下可以获得的最大收益。
     * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][][] dp = new int[length][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        // 最多交易两次，不是实际交易次数
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
        return dp[length - 1][2][0];
    }
}
