package backtracking;

/**
 * @Author: Druid
 * @Date: 2023/8/17 15:17
 * @Description: 79. 单词搜索
 */
public class WordSearch {

    /**
     * 思路：DFS + 回溯。类似字典树的解法，判断当前遍历到的字符是否是字符串的前缀。
     * <p>
     * 相关题目：
     * {@link trie.WordSearchII}
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, i, j, 0, word.toCharArray())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int row, int col, int begin, char[] chars) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] == '#' || board[row][col] != chars[begin]) {
            return false;
        }

        if (begin == chars.length - 1) {
            return board[row][col] == chars[begin];
        }

        // 回溯之后恢复原字符
        char originChar = board[row][col];
        // 标记该字符已经遍历过
        board[row][col] = '#';

        if (dfs(board, row - 1, col, begin + 1, chars) || dfs(board, row + 1, col, begin + 1, chars)
                || dfs(board, row, col - 1, begin + 1, chars) || dfs(board, row, col + 1, begin + 1, chars)) {
            board[row][col] = originChar;
            return true;
        } else {
            board[row][col] = originChar;
            return false;
        }
    }
}
