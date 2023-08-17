package greedy;

/**
 * @Author: Druid
 * @Date: 2023/5/10 10:41
 * @Description: 45. 跳跃游戏 II
 */
public class JumpGameII {

    /**
     * 思路：遍历数组，计算每一个起跳点所能跳到的最大位置，在到达该最大位置前，计算下一次跳跃可以到达的最大位置。
     * 每次到达最大的跳跃位置后，跳跃次数加 1，继续上面的操作，直至终点。
     */
    public int jump(int[] nums) {
        int count = 0;
        int end = 0;
        int maxPos = 0;
        // 遍到数组的倒数第二位，否则会多计算一次跳跃
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                end = maxPos;
                count++;
            }
        }
        return count;
    }
}
