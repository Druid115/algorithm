package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/9/7 9:56
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 思路：计算下边界时，当 nums[mid] >= target 时，right = mid - 1；nums[mid] < target 时，left = mid + 1，返回 left；
     * 计算上边界时，当 nums[mid] > target 时，right = mid - 1；nums[mid] <= target 时 left = mid + 1，返回 right。
     */
    public int[] searchRange(int[] nums, int target) {
        int left = -1;
        int right = -1;
        int low = 0;
        int high = nums.length - 1;
        // 寻找左边界
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 注意边界条件
        if (low == nums.length || nums[low] != target) {
            return new int[]{left, right};
        }
        left = low;

        low = 0;
        high = nums.length - 1;
        // 寻找右边界
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        right = high;

        return new int[]{left, right};
    }


}
