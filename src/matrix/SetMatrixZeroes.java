package matrix;

/**
 * @Author: Druid
 * @Date: 2023/6/21 10:36
 * @Description: 73. 矩阵置零
 */
public class SetMatrixZeroes {

    /**
     * 思路：用 matrix 的第一行和第一列记录该行该列是否有 0，作为标志位。
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0Flag = false;
        boolean col0Flag = false;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0Flag = true;
                break;
            }
        }

        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0Flag = true;
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row0Flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0Flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 思路：同方法一，使用一个标记变量记录第一列是否原本存在 0，第一列的第一个元素即可以标记第一行是否出现 0
     */
    public void setZeroes2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean col0Flag = false;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0Flag = true;
            }
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 防止每一列的第一个元素被提前更新
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0Flag) {
                matrix[i][0] = 0;
            }
        }
    }
}
