/**
 * @Author: Druid
 * @Date: 2023/5/8 15:18
 * @Description: 189. 轮转数组
 */
public class RotateArray {

    /**
     * 思路：先计算出要轮转元素的个数 r，接着通过三次翻转数组（第一次整个数组，第二次前 r 个数子，第三次剩余部分的数字）即可。
     */
    public void rotate(int[] nums, int k) {
        int r = k % nums.length;

        reversal(nums, 0, nums.length - 1);
        reversal(nums, 0, r - 1);
        reversal(nums, r, nums.length - 1);
    }


    public void reversal(int[] nums, int start, int end) {
        while (start != end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
