package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Druid
 * @Date: 2023/6/25 11:48
 * @Description: 219. 存在重复元素 II
 */
public class ContainsDuplicateII {

    /**
     * 思路：使用 hash 表和滑动窗口，控制窗口的大小始终为 k。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
