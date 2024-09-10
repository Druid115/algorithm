package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/11/27 11:27
 * @Description: 198. 打家劫舍
 */
public class HouseRobber {

    /**
     * 思路：动态规划。子问题为从 k 个房子中能偷到的最大金额，状态转移方程为 dp[k] = max(dp[k - 1], nums[k - 1] + dp[k-2])
     */
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }
}
