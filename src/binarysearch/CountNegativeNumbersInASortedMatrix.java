package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/12/27 15:41
 * @Description: 1351. 统计有序矩阵中的负数
 */
public class CountNegativeNumbersInASortedMatrix {

    /**
     * 思路：分治 + 二分。
     */
    public int countNegatives(int[][] grid) {
        return solve(0, grid.length - 1, 0, grid[0].length - 1, grid);
    }

    private int solve(int low, int high, int left, int right, int[][] grid) {
        if (low > high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int pos = -1;
        for (int i = left; i <= right; ++i) {
            if (grid[mid][i] < 0) {
                pos = i;
                break;
            }
        }
        int ans = 0;
        if (pos != -1) {
            ans += grid[0].length - pos;
            ans += solve(low, mid - 1, pos, right, grid);
            ans += solve(mid + 1, high, left, pos, grid);
        } else {
            ans += solve(mid + 1, high, left, right, grid);
        }
        return ans;
    }

    /**
     * 思路：同 74. 搜索二维矩阵。
     * <p>
     * 相关题目：
     * {@link SearchA2DMatrix}
     */
    public int countNegatives2(int[][] grid) {
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        int i = 0;
        int j = col - 1;

        while (i < grid.length && j >= 0) {
            // 如果当前值大于等于 0，那么前面的值肯定都非负，那么直接跳过，进入下一行
            if (grid[i][j] >= 0) {
                i++;
            } else {
                // 如果当前值小于 0，那么当前值以及同列下的值都小于 0 ，直接添加，然后进入下一列
                count += row - i;
                j--;
            }
        }
        return count;
    }
}