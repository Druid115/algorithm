package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/8/3 18:27
 * @Description: 200. 岛屿数量
 */
public class NumberOfIslands {

    /**
     * 思路：广度优先遍历。
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        int[] direction = new int[]{-1, 0, 1, 0, -1};
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '-';
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        for (int k = 0; k < direction.length - 1; k++) {
                            int x = poll[0] + direction[k];
                            int y = poll[1] + direction[k + 1];

                            if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == '1') {
                                grid[x][y] = '-';
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 思路：深度优先遍历。
     */
    public int numIslands2(char[][] grid) {
        int isLandsNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    isLandsNum++;
                    dfs(grid, i, j);
                }
            }
        }
        return isLandsNum;
    }

    public void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '-';

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    static class UnionFind {
        /**
         * 连通分量个数
         */
        private int count;

        private int[] parent;

        private int[] rank;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        private int find(int x) {
            while (x != parent[x]) {
                // 压缩层级
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        }
    }

    /**
     * 思路：并查集。并查集中维护连通分量的个数，相邻的陆地（只需要向右看和向下看）合并，只要发生过合并，岛屿的数量就减少 1。
     * 在遍历的过程中，同时记录空地的数量。最后并查集中连通分量的个数 - 空地的个数，就是岛屿数量。
     */
    public int numIslands3(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // 空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(row * col);
        int[][] directions = {{1, 0}, {0, 1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    spaces++;
                } else {
                    int idx = i * col + j;
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地。第一行向下尝试合并，与第二行向上尝试合并是同一个操作。合并是双向的，搜索是单向的。
                        if (x < row && y < col && grid[x][y] == '1') {
                            unionFind.union(idx, x * col + y);
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }
}
