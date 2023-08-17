package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/6/12 15:50
 * @Description: 167. 两数之和 II - 输入有序数组
 */
public class TwoSumII {

    /**
     * 思路：双指针。
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int tmp = numbers[left] + numbers[right];
            if (tmp < target) {
                do {
                    left++;
                } while (numbers[left] == numbers[left - 1]);
            } else if (tmp > target) {
                do {
                    right--;
                } while (numbers[right] == numbers[right + 1]);
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
