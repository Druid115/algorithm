package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/10 14:33
 * @Description: 909. 蛇梯棋
 */
public class SnakesAndLadders {

    /**
     * 思路：问题等价于在这张有向图上求出从 1 到 N^2 的最短路径长度。使用广度优先搜索。
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] nums = new int[n * n + 1];

        // 扁平化处理
        int index = 0;
        boolean isRight = true;
        for (int i = n - 1; i >= 0; i--) {
            if (isRight) {
                for (int j = 0; j < n; j++) {
                    nums[++index] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    nums[++index] = board[i][j];
                }
            }
            isRight = !isRight;
        }

        Queue<Integer> queue = new LinkedList<>();
        // 存储当前值，以及当前 step
        Map<Integer, Integer> map = new HashMap<>();
        queue.offer(1);
        map.put(1, 0);

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int step = map.get(poll);
            if (poll == n * n) {
                return step;
            }

            // 当前值可到达的范围
            for (int i = 1; i <= 6; i++) {
                int np = poll + i;
                if (np > n * n) {
                    break;
                }
                if (nums[np] != -1) {
                    np = nums[np];
                }
                // 如果已经遍历过则跳过
                if (!map.containsKey(np)) {
                    map.put(np, step + 1);
                    queue.offer(np);
                }
            }
        }
        return -1;
    }
}
