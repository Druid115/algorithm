package backtracking;

/**
 * @Author: Druid
 * @Date: 2023/8/18 17:13
 * @Description: 52. N 皇后 II
 */
public class NQueensII {

    private int res;

    /**
     * 思路：同 51. N 皇后。
     */
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] main = new boolean[2 * n - 1];
        boolean[] sub = new boolean[2 * n - 1];

        dfs(0, n, col, main, sub);
        return res;
    }

    private void dfs(int row, int n, boolean[] col, boolean[] main, boolean[] sub) {
        if (row == n) {
            res++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if (!col[j] && !main[row - j + n - 1] && !sub[row + j]) {

                col[j] = true;
                main[row - j + n - 1] = true;
                sub[row + j] = true;

                dfs(row + 1, n, col, main, sub);

                col[j] = false;
                main[row - j + n - 1] = false;
                sub[row + j] = false;
            }
        }
    }
}
