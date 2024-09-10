package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/9/26 16:32
 * @Description: 502. IPO
 */
public class IPO {

    /**
     * 思路：利用堆的贪心算法。每完成一个任务会使得总资金 w 增加或者不变，因此对于所选的第 i 个任务而言，应该在所有未被选择且启动资金不超过 w 的所有任务里面选利润最大的。
     * 首先将项目按照所需资本的从小到大进行排序，每次进行选择时，从所有投入资本小于等于 w 的项目中选择利润最大的项目 j，然后更新手中持有的资本为 w + profits[j]，
     * 同时再从所有花费资本小于等于 w + profits[j] 的项目中选择，按照上述规则不断选择 k 次即可。
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int len = profits.length;
        int[][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        Queue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        int i = 0;
        while (k > 0) {
            while (i < len && arr[i][0] <= w) {
                queue.offer(arr[i][1]);
                i++;
            }
            if (queue.isEmpty()) {
                break;
            }
            w += queue.poll();
            k--;
        }
        return w;
    }
}
