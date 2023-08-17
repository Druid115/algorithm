package interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Druid
 * @Date: 2023/6/26 16:35
 * @Description: 452. 用最少数量的箭引爆气球
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    /**
     * 思路：根据区间右边界对数组进行排序，这样可以杜绝了前面某个区间包含后面区间的情况。
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));

        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            // 判断是否需要更新右边界
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }
}
