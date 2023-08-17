package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/5/8 10:40
 * @Description: 27. 移除元素
 */
public class RemoveElement {

    /**
     * 要求不占用额外的空间，并且将剩下的元素转移到一边。
     * 思路：使用双指针，进行数字交换。
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;

        // 指定右指针为数组末尾，能够省去数组为空、数组只有一个元素等情况的判断
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
