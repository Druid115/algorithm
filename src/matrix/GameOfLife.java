package matrix;

/**
 * @Author: Druid
 * @Date: 2023/6/21 14:54
 * @Description: 289. 生命游戏
 */
public class GameOfLife {

    /**
     * 思路：引入额外的两种状态来标识细胞的变化，最后对状态进行回归。
     */
    public void gameOfLife(int[][] board) {
        int[] direction = new int[]{-1, 0, 1};
        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int liveNeighbor = 0;

                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {

                        // 跳过自己的位置
                        if (direction[m] == 0 && direction[n] == 0) {
                            continue;
                        }
                        int x = i + direction[m];
                        int y = j + direction[n];

                        // 查看相邻的细胞是否是活细胞
                        if (x >= 0 && x < row && y >= 0 && y < col && Math.abs(board[x][y]) == 1) {
                            liveNeighbor++;
                        }
                    }
                }

                // 活细胞
                if (board[i][j] == 1 && (liveNeighbor < 2 || liveNeighbor > 3)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[i][j] = -1;
                }

                // 死细胞
                if (board[i][j] == 0 && liveNeighbor == 3) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[i][j] = 2;
                }
            }
        }

        // 遍历 board 得到一次更新后的状态
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
