package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/8/7 11:18
 * @Description: 130. 被围绕的区域
 */
public class SurroundedRegions {

    /**
     * 思路：深度优先遍历。遍历边界上每一个为 O 的单元格，将与之相连的单元格置为 #。
     * 完成后再次遍历数组，将内容为 # 的单元格置为 O，其余单元格置为 X。
     */
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') {
            return;
        }
        board[row][col] = '#';

        dfs(board, row - 1, col);
        dfs(board, row + 1, col);
        dfs(board, row, col - 1);
        dfs(board, row, col + 1);
    }

    /**
     * 思路：广度优先遍历。
     */
    public void solve2(char[][] board) {
        int[] direction = new int[]{-1, 0, 1, 0, -1};
        int row = board.length;
        int col = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    if (board[i][j] == 'O') {
                        board[i][j] = '#';
                        queue.offer(new int[]{i, j});
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
                            for (int k = 0; k < direction.length - 1; k++) {
                                int x = poll[0] + direction[k];
                                int y = poll[1] + direction[k + 1];

                                if (x >= 0 && x < row && y >= 0 && y < col && board[x][y] == 'O') {
                                    board[x][y] = '#';
                                    queue.offer(new int[]{x, y});
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
