package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/9/5 14:19
 * @Description: 162. 寻找峰值
 */
public class FindPeakElement {

    /**
     * 思路：只要数组中存在一个元素比左边或右边的元素大，那么沿着它一定可以找到一个峰值。
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
