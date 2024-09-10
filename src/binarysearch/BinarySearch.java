package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/12/27 13:56
 * @Description: 704. 二分查找
 */
public class BinarySearch {

    /**
     * 思路：区间的定义就是不变量，在循环中坚持根据查找区间的定义来做边界处理，就是循环不变量规则。
     */
    public int search(int[] nums, int target) {
        // 区间为 [left, right]
        int left = 0;
        int right = nums.length - 1;
        // left == right 是有意义的
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
