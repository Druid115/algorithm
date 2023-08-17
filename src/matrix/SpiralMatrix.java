package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/20 16:59
 * @Description: 54. 螺旋矩阵
 */
public class SpiralMatrix {

    /**
     * 思路：使用四个指针分别标识上、下、左、右边界，顺时针遍历，并不断更新边界。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            for (int col = left; col <= right; col++) {
                res.add(matrix[top][col]);
            }
            if (++top > bottom) {
                break;
            }

            for (int row = top; row <= bottom; row++) {
                res.add(matrix[row][right]);
            }
            if (--right < left) {
                break;
            }

            for (int col = right; col >= left; col--) {
                res.add(matrix[bottom][col]);
            }
            if (--bottom < top) {
                break;
            }

            for (int row = bottom; row >= top; row--) {
                res.add(matrix[row][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }
}
