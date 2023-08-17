/**
 * @Author: Druid
 * @Date: 2023/5/11 16:02
 * @Description: 238. 除自身以外数组的乘积
 */
public class ProductOfArrayExceptSelf {

    /**
     * 思路：可以使用两个数组来记录每个位置左右两侧的元素乘积，在此基础上可以将空间复杂度优化到 O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        // res[i] 表示 i 左侧所有元素的乘积，0 左侧没有元素，所以为 1。
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // R 为右侧所有元素的乘积，刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            // 对于索引 j，左边的乘积为 answer[j]，右边的乘积为 R
            res[j] = res[j] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R = R * nums[j];
        }

        return res;
    }
}
