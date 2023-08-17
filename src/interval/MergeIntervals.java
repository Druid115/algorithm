package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/26 10:41
 * @Description: 56. 合并区间
 */
public class MergeIntervals {

    /**
     * 思路：先根据区间起始位置对数组进行排序，再进行合并。前一个区间的右边界小于当前区间的左边界，则新增区间，否则更新最后一个区间的右边界。
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            // 合并的列表为空，或者最后一个区间的右边界小于当前区间的左边界
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                // 更新合并列表中最后一个区间的右边界
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
