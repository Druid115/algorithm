package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/9/4 15:15
 * @Description: 35. 搜索插入位置
 */
public class SearchInsertPosition {

    /**
     * 思路：二分查找，找出第 1 个大于等于目标元素的下标。
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 退出循环后，low 的位置比 high 大 1
        return low;
    }
}
