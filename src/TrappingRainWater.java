import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Druid
 * @Date: 2023/5/12 17:43
 * @Description: 42. 接雨水
 */
public class TrappingRainWater {

    /**
     * 思路：动态规划。当某根柱子左右两侧都存在一个比它高的柱子时，这三根主子间可以生成水滴。
     * 可以用两个数组来保存位置 i 左右两侧最高的柱子的高度，从中选择较矮的一个，减去当前柱子的高度即为水滴的高度。
     */
    public int trap(int[] height) {
        int ans = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[height.length - 1] = height[height.length - 1];
        for (int j = height.length - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }

        for (int k = 1; k < height.length - 1; k++) {
            ans += Math.min(leftMax[k], rightMax[k]) - height[k];
        }

        return ans;
    }

    /**
     * 思路：动态规划的优化版本，假设两柱子分别为 i，j。那么就有 iLeftMax，iRightMax，jLeftMx，jRightMax 这四个变量。
     * 由于 j > i，故 jLeftMax >= iLeftMax，iRightMax >= jRightMax。
     * 如果 iLeftMax > jRightMax，则必有 jLeftMax >= jRightMax，所以我们能接 j 点的水。
     * 如果 jRightMax > iLeftMax，则必有 iRightMax >= iLeftMax，所以我们能接 i 点的水。
     */
    public int trap2(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                // 对应 jRightMax > iLeftMax 的情况
                ans += leftMax - height[left];
                left++;
            } else {
                // 对应 iLeftMax > jRightMax 的情况
                ans += rightMax - height[right];
                right--;
            }
        }

        return ans;
    }

    /**
     * 思路：单调栈，栈中保存柱子高度的递减序列。当栈顶元素小于当前柱子的高度时，说明栈中柱子可能存在左右两边柱子比较高的情况，
     * 进行出栈处理，并计算可以形成雨水的面积。
     */
    public int trap3(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer idx = stack.pop();
                // pop 出相等的元素
                while (!stack.isEmpty() && height[stack.peek()] == height[idx]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    // 左边界的位置，右边界是当前的柱体
                    Integer left = stack.peek();
                    // 比较左右两边柱子的高度，较低的柱子减去 pop 出来的柱子就是雨水的高度，i - stackTop - 1 是雨水的宽度。
                    ans += (Math.min(height[left], height[i]) - height[idx]) * (i - left - 1);
                }
            }
            stack.push(i);
        }
        return ans;
    }
}
