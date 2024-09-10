package dynamicprogramming;


import java.util.Arrays;

/**
 * @Author: Druid
 * @Date: 2023/11/29 11:36
 * @Description: 322. 零钱兑换
 */
public class CoinChange {

    /**
     * 思路：动态规划。dp[i] = min(dp[i] - coins[j]) + 1，其中 j 从 0 到 coins.length - 1。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 最大次数不可能超过总金额
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 思路：DFS + 回溯。
     */
    public int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        return dfs(coins, amount, memo);
    }

    public int dfs(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, memo);
            if (res >= 0 && res < min) {
                // 加上得到 res 结果的那个步骤中，兑换的一个硬币
                min = res + 1;
            }
        }
        memo[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[amount];
    }
}
