package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/5/8 11:27
 * @Description: 80. 删除有序数组中的重复项 II
 */
public class RemoveDuplicatesFromSortedArrayII {

    /**
     * 思路：遍历数组，直接保留前 2 个数字，之后，与当前写入的位置前面的第 2 个元素进行比较，不相同则保留
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (index < 2 || num != nums[index - 2]) {
                nums[index++] = num;
            }
        }
        return index;
    }
}
