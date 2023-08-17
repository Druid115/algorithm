package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Druid
 * @Date: 2023/8/15 11:10
 * @Description: 212. 单词搜索 II
 */
public class WordSearchII {

    static class TrieNode {
        String word;
        boolean end;
        TrieNode[] next = new TrieNode[26];
    }

    public void insert(String word) {
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new TrieNode();
            }
            cur = cur.next[idx];
        }
        cur.end = true;
        cur.word = word;
    }

    private TrieNode root;

    /**
     * 思路：回溯 + 字典树。将 words 数组中的字符串构造成一棵字典树，在 DFS 的过程中，判断当前路径是否是字典树的一个前缀，如果不是，则进行剪枝。
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        this.root = new TrieNode();
        for (String s : words) {
            insert(s);
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, i, j, this.root, res);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int row, int col, TrieNode node, Set<String> res) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '#') {
            return;
        }
        // 回溯之后恢复原字符
        char originChar = board[row][col];
        int idx = originChar - 'a';
        if (node.next[idx] == null) {
            return;
        }
        node = node.next[idx];
        if (node.word != null) {
            res.add(node.word);
        }
        // 标记该字符已经遍历过
        board[row][col] = '#';

        dfs(board, row - 1, col, node, res);
        dfs(board, row + 1, col, node, res);
        dfs(board, row, col - 1, node, res);
        dfs(board, row, col + 1, node, res);

        board[row][col] = originChar;
    }
}
