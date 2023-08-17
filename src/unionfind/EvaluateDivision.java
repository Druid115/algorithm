package unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/8/8 11:39
 * @Description: 399. 除法求值
 */
public class EvaluateDivision {

    static class UnionFind {

        private int[] parent;

        /**
         * 指向父结点的权值
         */
        private double[] weight;

        public UnionFind(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0;
            }
        }

        private int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
            /*
               rootX — rootY
                /       /
               x   —   y
               两条路径有向边的权值乘积相等：weight[x] * weight[rootX] = value * weight[y]
             */
            weight[rootX] = weight[y] * value / weight[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0;
            }
        }
    }

    /**
     * 思路：并查集。
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();
        UnionFind unionFind = new UnionFind(2 * equationsSize);

        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }
}
