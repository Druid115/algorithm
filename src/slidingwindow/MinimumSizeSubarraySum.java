package slidingwindow;

/**
 * @Author: Druid
 * @Date: 2023/6/13 18:06
 * @Description: 209. 长度最小的子数组
 */
public class MinimumSizeSubarraySum {

    /**
     * 思路：使用滑动窗口，窗口内的元素之和大于 target 时，移动左边界，直到元素之和小于 target，在移动的过程中更新最短的长度。
     */
    public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1;
        int left = 0;
        int right = 0;
        int tmp = 0;

        while (right < nums.length) {
            tmp += nums[right];
            while (tmp >= target) {
                res = Math.min(res, right - left + 1);
                tmp = tmp - nums[left];
                left++;
            }
            right++;
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
