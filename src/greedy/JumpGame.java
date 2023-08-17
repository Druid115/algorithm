package greedy;

/**
 * @Author: Druid
 * @Date: 2023/5/9 16:03
 * @Description: 55. 跳跃游戏
 */
public class JumpGame {

    /**
     * 思路：遍历数组，更新能跳到的最远的距离。
     * <p>
     * 相关题目：
     * {@link JumpGameII}
     */
    public boolean canJump(int[] nums) {
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要先判断能不能到达当前位置，再更新值
            if (step < i) {
                return false;
            }
            step = Math.max(step, i + nums[i]);
        }
        return true;
    }
}
