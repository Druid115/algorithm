package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/8/16 11:40
 * @Description: 77. 组合
 */
public class Combinations {

    /**
     * 思路：DFS + 回溯。搜索起点和当前还需要选几个数有关，而当前还需要选几个数与已经选了几个数有关，因此剪枝条件：搜索起点的上界 + 接下来要选择的元素个数 - 1 = n。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int start, int n, int k, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            dfs(i + 1, n, k, list, res);
            list.remove(list.size() - 1);
        }
    }
}
