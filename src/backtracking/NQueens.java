package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/8/18 15:55
 * @Description: 51. N 皇后
 */
public class NQueens {

    /**
     * 思路：使用三个数组分别记录每个皇后所在的列、主对角线和副对角线，依次在每一行放置一个皇后。
     * 每次新放置皇后时，判断这三个数组是否有对应的记录，若有则进行剪枝。直到遍历到最后一层，将找到的解放入返回列表。
     * 主对角线的规律：每条对角线的 row - col 为一个固定值。
     * 副对角线的规律：每条对角线的 row + col 为一个固定值。
     * <p>
     * 相关题目：
     * {@link NQueensII}
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 记录某一列是否放置了皇后
        boolean[] col = new boolean[n];
        // 记录主对角线上的单元格是否放置了皇后
        boolean[] main = new boolean[2 * n - 1];
        // 记录了副对角线上的单元格是否放置了皇后
        boolean[] sub = new boolean[2 * n - 1];

        dfs(0, n, col, main, sub, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int row, int n, boolean[] col, boolean[] main, boolean[] sub, List<Integer> list, List<List<String>> res) {
        if (row == n) {
            List<String> board = convert2board(list, n);
            res.add(board);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (!col[j] && !main[row - j + n - 1] && !sub[row + j]) {
                list.add(j);
                col[j] = true;
                main[row - j + n - 1] = true;
                sub[row + j] = true;

                dfs(row + 1, n, col, main, sub, list, res);

                list.remove(list.size() - 1);
                col[j] = false;
                main[row - j + n - 1] = false;
                sub[row + j] = false;
            }
        }
    }

    private List<String> convert2board(List<Integer> list, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : list) {
            StringBuilder row = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == num) {
                    row.append("Q");
                } else {
                    row.append(".");
                }
            }
            board.add(row.toString());
        }
        return board;
    }

}
