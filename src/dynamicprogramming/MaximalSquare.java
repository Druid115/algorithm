package dynamicprogramming;

/**
 * @Author: Druid
 * @Date: 2023/12/20 15:11
 * @Description: 221. 最大正方形
 */
public class MaximalSquare {

    /**
     * 思路：动态规划。dp[i + 1, j + 1] 表示以 matrix[i, j] 为右下角的正方形的最大边长。
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                    maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
