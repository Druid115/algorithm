package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/6/25 11:13
 * @Description: 1. 两数之和
 */
public class TwoSum {

    /**
     * 思路：使用 hash 表记录每个数字出现的位置。
     *
     * <p>
     * 相关题目：
     * {@link twopointers.TwoSumII}
     * {@link twopointers.ThreeSum}
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
