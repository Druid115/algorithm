package interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/26 11:29
 * @Description: 57. 插入区间
 */
public class InsertInterval {

    /**
     * 思路：当我们遍历到区间 [l,r] 时，分三种情况：
     * 1. 如果 r < left，说明区间 [l,r] 与 S 不重叠并且在其左侧
     * 2. 如果 l > right，说明区间 [l,r] 与 S 不重叠并且在其右侧
     * 3. 如果上面两种情况均不满足，说明区间 [l,r] 与 S 重叠，此时需要更新 left 和 right 的值
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
