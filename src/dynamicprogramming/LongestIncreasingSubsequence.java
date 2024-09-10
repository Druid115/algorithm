package dynamicprogramming;

import java.util.Arrays;

/**
 * @Author: Druid
 * @Date: 2023/11/29 16:53
 * @Description: 300. 最长递增子序列
 */
public class LongestIncreasingSubsequence {

    /**
     * 思路：动态规划。dp[i] 表示以 nums[i] 结尾的上升子序列的长度，其中 nums[i] 必选。
     * 只要 nums[i] 大于之前的某个数字，那么 nums[i] 就可以接在这个数后面形成一个更长的上升子序列。
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }

    /**
     * 思路：贪心 + 二分查找。考虑一个简单的贪心，如果要使上升子序列尽可能长，则需要让序列上升得尽可能慢，因此每次在上升子序列最后加上的那个数尽可能小。
     * 维护一个数组 tail，表示长度为 i 的所有上升子序列的结尾的最小值，用 len 记录目前最长上升子序列的长度，tail[1] = nums[0]，随后遍历数组 nums。
     * 如果 nums[i] 严格大于有序数组 tail 的最后一个元素，就把 num[i] 放在 tail 的后面；否则在 tail 中找到第一个比 nums[i] 大的数 tail[k]，
     * 并更新 tail[k] = nums[i]，这样就找到了一个结尾更小的相同长度的上升子序列。
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1;
        int[] tail = new int[nums.length + 1];
        tail[len] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tail[len]) {
                tail[++len] = nums[i];
            } else {
                int left = 1;
                int right = len;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (tail[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                tail[left] = nums[i];
            }
        }
        return len;
    }
}
