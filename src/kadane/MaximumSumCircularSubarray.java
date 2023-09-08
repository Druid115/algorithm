package kadane;

/**
 * @Author: Druid
 * @Date: 2023/9/4 10:35
 * @Description: 918. 环形子数组的最大和
 */
public class MaximumSumCircularSubarray {

    /**
     * 思路：环形子数组的最大和具有两种可能情况，一种是不使用环的情况，另一种是使用环的情况（一部分在首部，一部分在尾部）。
     * 第二种情况使用到了环，则说明在构成最大和的首尾数组中间的子数组中必定包含负数。可以将第二种情况转换为第一种情况。
     * max(前缀数组 + 后缀数组) = max(数组总和 - subarray) （subarray 指的是前缀数组和后缀数组中间的数组）
     *                       = 数组总和 + max(-subarray)
     *                       = 数组总和 - min(subarray) 把负号提出来，max 变成 min
     * 极端情况如果这数组的所有数都是负数，二种情况下结果为 0（最小的子数组就是本数组，total - total = 0）。所以多加一个判断，最大子数组和小于 0 时，直接返回该结果。
     */
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int maxSum = nums[0];
        int curMax = 0;
        int minSum = nums[0];
        int curMin = 0;

        for (int num : nums) {
            curMax = Math.max(num, curMax + num);
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(num, curMin + num);
            minSum = Math.min(minSum, curMin);

            total += num;
        }

        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
