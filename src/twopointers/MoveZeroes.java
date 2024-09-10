package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/12/21 11:44
 * @Description: 283. 移动零
 */
public class MoveZeroes {

    /**
     * 思路：使用两个指针，左指针指向第一个为 0 的位置，右指针指向在这个位置右边第一个不为 0 的位置。
     */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
