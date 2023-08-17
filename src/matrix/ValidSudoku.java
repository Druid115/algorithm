package matrix;

/**
 * @Author: Druid
 * @Date: 2023/6/20 16:11
 * @Description: 36. 有效的数独
 */
public class ValidSudoku {

    /**
     * 思路：使用三个数组，分别记录行、列和九宫格中每个数字出现的次数。
     */
    public boolean isValidSudoku(char[][] board) {
        // 第二维统一为 10，确保能够记录数字 9 的次数
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] cell = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }

                int num = board[i][j] - '0';
                // 确定该位置在第几个九宫格中
                int k = i / 3 * 3 + j / 3;

                if (row[i][num] == 1 || col[j][num] == 1 || cell[k][num] == 1) {
                    return false;
                }
                row[i][num] = col[j][num] = cell[k][num] = 1;
            }
        }
        return true;
    }
}
