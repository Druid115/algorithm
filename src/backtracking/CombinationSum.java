package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/8/17 10:10
 * @Description: 39. 组合总和
 */
public class CombinationSum {

    /**
     * 思路：DFS + 回溯。剪枝条件：每一次搜索的时候设置下一轮搜索的起点，起点之前的位置不用遍历。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 可以提升剪枝的速度
        Arrays.sort(candidates);
        dfs(0, candidates, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int start, int[] candidates, int target, List<Integer> list, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(i, candidates, target - candidates[i], list, res);
            list.remove(list.size() - 1);
        }
    }
}
