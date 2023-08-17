package greedy;

/**
 * @Author: Druid
 * @Date: 2023/6/12 17:11
 * @Description: 11. 盛最多水的容器
 */
public class ContainerWithMostWater {

    /**
     * 思路：使用双指针，计算两个指针间的面积，并更新面积最大值，然后将高度较小的指针向内移动一格，循环往复，直到两个指针相遇。
     * 向内移动指针那么底边会变短，如果移动短板，短板有可能会变长，面积有可能变大。如果移动长板，短板可能不变，面积一定变小。
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(height[left] * (right - left), max);
                left++;
            } else {
                max = Math.max(height[right] * (right - left), max);
                right--;
            }
        }
        return max;
    }
}
