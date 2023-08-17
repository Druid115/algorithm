/**
 * @Author: Druid
 * @Date: 2023/4/25 18:14
 * @Description: 88. 合并两个有序数组
 */
public class MergeSortedArray {

    /**
     * 数组 1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0。
     * 思路：在数组 1 中，从后往前进行插入操作。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }
            k--;
        }

        // 只需考虑数组 2 没有遍历完成的情况
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
