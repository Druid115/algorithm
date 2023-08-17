package matrix;

/**
 * @Author: Druid
 * @Date: 2023/6/21 10:13
 * @Description: 48. 旋转图像
 */
public class RotateImage {

    /**
     * 思路：两次翻转，先将其通过水平轴翻，再根据主对角线翻转。matrix[row][col] ——> matrix[n - row - 1][col] ——> matrix[col][n - row - 1]
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[length - 1 - i];
            matrix[length - 1 - i] = temp;
        }

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
