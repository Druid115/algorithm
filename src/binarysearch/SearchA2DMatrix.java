package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/9/4 17:08
 * @Description: 74. 搜索二维矩阵
 */
public class SearchA2DMatrix {

    /**
     * 思路：先对第一列的元素进行二分查找，找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找目标值是否存在。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int top = 0;
        int bottom = row - 1;
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }

        // 注意判断边界条件
        if (bottom < 0 || matrix[bottom][col - 1] < target) {
            return false;
        }

        int left = 0;
        int right = col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[bottom][mid] == target) {
                return true;
            } else if (matrix[bottom][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 思路：将二维矩阵当做一维矩阵来做，整体进行二分查找。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int low = 0;
        int high = row * col - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int x = matrix[mid / col][mid % col];
            if (x == target) {
                return true;
            } else if (x < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
