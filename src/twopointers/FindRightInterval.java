package twopointers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Druid
 * @Date: 2024/1/3 11:36
 * @Description: 436. 寻找右区间
 */
public class FindRightInterval {

    /**
     * 思路：将 start 和 end 分别抽取到另外的数组中，并保存对应的位置下标。按照右端点从小到大的顺序进行遍历，找出最小的左端点。
     * 最右区间的左端点也具有单调特性，因此无需每次从头开始对 start 数组进行遍历。
     */
    public int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] res = new int[length];
        int[][] start = new int[length][2];
        int[][] end = new int[length][2];
        for (int i = 0; i < length; i++) {
            start[i][0] = intervals[i][0];
            start[i][1] = i;
            end[i][0] = intervals[i][1];
            end[i][1] = i;
        }
        Arrays.sort(start, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(end, Comparator.comparingInt(a -> a[0]));

        for (int i = 0, j = 0; i < length; i++) {
            int cur = end[i][0];
            int idx = end[i][1];
            while (j < length && start[j][0] < cur) {
                j++;
            }
            res[idx] = j == length ? -1 : start[j][1];
        }
        return res;
    }
}
