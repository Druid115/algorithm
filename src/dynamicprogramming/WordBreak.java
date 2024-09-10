package dynamicprogramming;

import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/11/27 15:59
 * @Description: 139. 单词拆分
 */
public class WordBreak {

    /**
     * 思路：动态规划。dp[i] 表示 s 中 [0, i - 1] 的字串能否由单词表组成。
     * 用指针 j 去划分 [0, i - 1] 子串，dp[i] 是否为 true 取决于两点：
     * 1. 前缀子串 [0，j - 1] 的 dp[j]，是否为 true；
     * 2. 剩余子串 [j，i - 1]，是否是单词表的单词。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    /**
     * 思路：DFS + 回溯，指针从左往右扫描，考察所有的拆分可能。如果指针的左侧部分是单词，则对剩余子串递归考察；如果指针的左侧部分不是单词，回溯考察别的分支。
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int[] visited = new int[s.length() + 1];
        return dfs(s, wordDict, 0, visited);
    }

    public boolean dfs(String s, List<String> wordDict, int start, int[] visited) {
        if (start == s.length()) {
            return true;
        }
        if (visited[start] != 0) {
            return visited[start] == 1;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(start, i)) && dfs(s, wordDict, i, visited)) {
                // 标记匹配成功
                visited[start] = 1;
                return true;
            }
        }
        // 标记匹配失败
        visited[start] = -1;
        return false;
    }
}
