package greedy;

import java.util.Arrays;

/**
 * @Author: Druid
 * @Date: 2023/5/12 11:21
 * @Description: 135. 分发糖果
 */
public class Candy {

    /**
     * 思路：初始化每个人拥有一个糖果，对 ratings 数组从左往右遍历，确定右边评分大于左边的情况，再从右往左遍历，确定左边评分大于右边的情况。
     */
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 需要注意，该位置的糖果数量应该取左侧遍历和右侧遍历中的最大值
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        return Arrays.stream(candy).sum();
    }
}
