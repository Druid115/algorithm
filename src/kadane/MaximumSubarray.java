package kadane;

/**
 * @Author: Druid
 * @Date: 2023/9/1 15:09
 * @Description: 53. 最大子数组和
 */
public class MaximumSubarray {

    /**
     * 思路：动态规划。令 dp[i] 为以 nums[i] 结尾的最大子段和，dp[i] = max{ nums[i], dp[i − 1] + nums[i] }
     */
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = num + Math.max(sum, 0);
            res = Math.max(sum, res);
        }
        return res;
    }
}
