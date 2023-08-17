package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/5/8 11:10
 * @Description: 26. 删除有序数组中的重复项
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * 思路：采用双指针，左指针指向不重复部分的最后一个元素，
     * 右指针遍历整个数组，遇到非重复的元素，将其加入到不重复部分，同时更新左指针的位置。
     * <p>
     * 相关题目：
     * {@link RemoveDuplicatesFromSortedArrayII}
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                // 这里可以添加一个对两个指针的间隔判断，当距离大于 1 时才进行交换，优化性能
                nums[++left] = nums[right];
            }
            right++;
        }

        return left + 1;
    }
}
