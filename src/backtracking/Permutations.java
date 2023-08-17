package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/8/16 16:14
 * @Description: 46. 全排列
 */
public class Permutations {

    /**
     * 思路：DFS + 回溯。需要维护一个 used 数组，记录当前数字是否使用过。
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), res);
        return res;
    }

    public static void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            list.add(nums[i]);
            used[i] = true;
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
