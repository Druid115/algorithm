import java.util.Arrays;

/**
 * @Author: Druid
 * @Date: 2023/5/10 11:29
 * @Description: 274. H 指数
 */
public class HIndex {

    /**
     * 思路：设置初始的 h 为 0，对数组进行排序，然后从后往前进行遍历。
     * 如果在遍历的过程中，当前引用数量大于 h，说明找到了一篇被引用了至少 h + 1 次的论文，所以将现有的 h 值加 1。继续遍历直到 h 无法继续增大。
     */
    public int hIndex(int[] citations) {
        int h = 0;
        Arrays.sort(citations);
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    /**
     * 思路：同方法 1，使用了一个数组 counter 用来记录当前引用次数的论文有几篇，对于引用次数超过论文发表数的情况，可以将其按照总的论文发表数来计算。
     * 最后我从后向前遍历数组 counter，在数组 counter 中得到大于或等于当前引用次数 i 的总论文数。
     */
    public int hIndex2(int[] citations) {
        int[] counter = new int[citations.length + 1];
        for (int citation : citations) {
            if (citation >= citations.length) {
                counter[citations.length]++;
            } else {
                counter[citation]++;
            }
        }
        int quote = 0;
        for (int i = counter.length - 1; i >= 0; i--) {
            quote += counter[i];
            if (quote >= i) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 思路：对论文数量进行二分查找。
     */
    public int hIndex3(int[] citations) {
        int len = citations.length;
        int left = 0;
        int right = len;
        while (left < right) {
            // 避免死循环
            int mid = (left + right + 1) / 2;
            // 满足高引用的特点是：引用次数 >= 论文篇数
            // count 的含义是：大于等于 mid 的论文篇数
            int count = 0;
            for (int citation : citations) {
                if (citation >= mid) {
                    count++;
                }
            }

            if (count >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
