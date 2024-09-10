package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/5/9 10:08
 * @Description: 121. 买卖股票的最佳时机
 */
public class BestTimeToBuyAndSellStock {

    /**
     * 思路：一次遍历，追踪对历史最低价格，并维护当前最大的收益。
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                res = Math.max(res, price - minPrice);
            }
        }

        return res;
    }

    /**
     * 思路：动态规划。dp[i][j] 表示天数 [0, i] 区间里，下标 i 这一天状态为 j 的时候能够获得的最大利润，其中：
     * j = 0，表示当前不持股；
     * j = 1，表示当前持股。
     * <p>
     * 相关题目：
     * {@link BestTimeToBuyAndSellStockII}
     * {@link BestTimeToBuyAndSellStockIII}
     * {@link BestTimeToBuyAndSellStockIV}
     */
    public int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 注意，只允许交易一次，因此手上的现金数就是当天的股价的相反数
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }


}
