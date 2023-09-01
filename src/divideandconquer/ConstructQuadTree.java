package divideandconquer;

/**
 * @Author: Druid
 * @Date: 2023/8/31 10:18
 * @Description: 427. 建立四叉树
 */
public class ConstructQuadTree {

    /**
     * 思路：以递归的方式构建四叉树。构建递归函数，使其能够返回以 (topRow, topCol) 为左上角，(bottomRow， bottomCol) 为右下角的矩阵的根节点。
     * 判断该矩阵是否为全 0 或全 1，如果是则直接创建根节点并返回，如果不是则创建根节点并递归创建四个子节点并进行赋值。
     */
    public Node construct(int[][] grid) {
        return constructHelper(grid, 0, 0, grid.length - 1, grid.length - 1);
    }

    private Node constructHelper(int[][] grid, int topRow, int topCol, int bottomRow, int bottomCol) {
        boolean isLeaf = true;
        int tmp = grid[topRow][topCol];
        for (int i = topRow; i <= bottomRow && isLeaf; i++) {
            for (int j = topCol; j <= bottomCol && isLeaf; j++) {
                if (grid[i][j] != tmp) {
                    isLeaf = false;
                }
            }
        }

        if (isLeaf) {
            return new Node(tmp == 1, true);
        }

        Node node = new Node(tmp == 1, false);
        int dx = bottomRow - topRow + 1;
        int dy = bottomCol - topCol + 1;
        // 划分四个子矩阵
        node.topLeft = constructHelper(grid, topRow, topCol, topRow + dx / 2 - 1, topCol + dy / 2 - 1);
        node.topRight = constructHelper(grid, topRow, topCol + dy / 2, topRow + dx / 2 - 1, bottomCol);
        node.bottomLeft = constructHelper(grid, topRow + dx / 2, topCol, bottomRow, topCol + dy / 2 - 1);
        node.bottomRight = constructHelper(grid, topRow + dx / 2, topCol + dy / 2, bottomRow, bottomCol);
        return node;
    }

    /**
     * 思路：使用前缀和优化判断矩阵全 0 或全 1 的过程。
     */
    public Node construct2(int[][] grid) {
        // 注意前缀矩阵的长度
        int[][] sum = new int[grid.length + 1][grid.length + 1];
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid.length; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return constructHelper2(grid, sum, 0, 0, grid.length - 1, grid.length - 1);
    }

    private Node constructHelper2(int[][] grid, int[][] sum, int topRow, int topCol, int bottomRow, int bottomCol) {
        // 注意区间的范围
        int cur = sum[bottomRow + 1][bottomCol + 1] - sum[bottomRow + 1][topCol] - sum[topRow][bottomCol + 1] + sum[topRow][topCol];
        int dx = bottomRow - topRow + 1;
        int dy = bottomCol - topCol + 1;
        int total = dx * dy;
        if (cur == 0 || cur == total) {
            return new Node(grid[topRow][topCol] == 1, true);
        }

        Node node = new Node(grid[topRow][topCol] == 1, false);
        node.topLeft = constructHelper2(grid, sum, topRow, topCol, topRow + dx / 2 - 1, topCol + dy / 2 - 1);
        node.topRight = constructHelper2(grid, sum, topRow, topCol + dy / 2, topRow + dx / 2 - 1, bottomCol);
        node.bottomLeft = constructHelper2(grid, sum, topRow + dx / 2, topCol, bottomRow, topCol + dy / 2 - 1);
        node.bottomRight = constructHelper2(grid, sum, topRow + dx / 2, topCol + dy / 2, bottomRow, bottomCol);

        return node;
    }
}
