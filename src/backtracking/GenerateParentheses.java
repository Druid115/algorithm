package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/8/17 11:29
 * @Description: 22. 括号生成
 */
public class GenerateParentheses {

    /**
     * 思路：DFS + 回溯。具体操作如下：
     * 1. 当前左右括号数量都小于 n 个的时候，可以产生分支；
     * 2. 产生的右括号数量大于左括号时，剪枝；
     * 3. 在括号数量都等于 n 的时候结束。
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0, 0, n, new StringBuilder(), res);
        return res;
    }

    private void dfs(int left, int right, int n, StringBuilder sb, List<String> res) {
        if (left == n && left == right) {
            res.add(sb.toString());
            return;
        }

        if (left < right) {
            return;
        }

        if (left < n) {
            sb.append('(');
            dfs(left + 1, right, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < n) {
            sb.append(')');
            dfs(left, right + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 思路：动态规划。dp[n + 1] = ( + dp[i] + ) + dp[n - 1 - i]，i >= 1。
     * dp[n] 必然是在 dp[n - 1] 上增加一对括号，这对括号必然将 dp[n-1] 的内容划分为两部分 dp[p] 和 dp[q]：( p ) q。
     */
    public List<String> generateParenthesis2(int n) {
        List<List<String>> dp = new ArrayList<>();
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        List<String> dp1 = new ArrayList<>();
        dp1.add("()");
        dp.add(dp1);

        for (int i = 2; i <= n; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String el = "(" + s1 + ")" + s2;
                        temp.add(el);
                    }
                }
            }
            dp.add(temp);
        }
        return dp.get(n);
    }
}
