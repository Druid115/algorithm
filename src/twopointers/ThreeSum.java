package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/12 17:41
 * @Description: 15. 三数之和
 */
public class ThreeSum {

    /**
     * 思路：排序后，转化为两数之和。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return res;
        }

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (List<Integer> list : twoSum(nums, i + 1, -nums[i])) {
                list.add(0, nums[i]);
                res.add(list);
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int i, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int left = i;
        int right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left] + nums[right];
            if (tmp < target) {
                left++;
            } else if (tmp > target) {
                right--;
            } else {
                res.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));
                // 跳过重复值
                do {
                    left++;
                } while (nums[left] == nums[left - 1] && left < right);

                do {
                    right--;
                } while (nums[right] == nums[right + 1] && left < right);
            }
        }
        return res;
    }
}
