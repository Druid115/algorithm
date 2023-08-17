package interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/26 10:08
 * @Description: 228. 汇总区间
 */
public class SummaryRanges {

    /**
     * 思路：使用两个指针分别标识区间的左右边界，不断地扩展右边界直到不满足条件。记录当前区间，并更新左边界的位置。
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (right == nums.length - 1 || nums[right] + 1 != nums[right + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[left]);
                if (left < right) {
                    sb.append("->").append(nums[right]);
                }
                res.add(sb.toString());
                left = right + 1;
            }
            right++;
        }
        return res;
    }
}
