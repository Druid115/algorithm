package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/8 9:57
 * @Description: 133. 克隆图
 */
public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    private Map<Node, Node> visited = new HashMap<>();

    /**
     * 思路：深度优先遍历。
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    /**
     * 思路：广度优先遍历。
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }

}
