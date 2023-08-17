package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/9 11:36
 * @Description: 207. 课程表
 */
public class CourseSchedule {

    /**
     * 思路：通过拓扑排序判断此课程安排图是否是有向无环图（DAG）。拓扑排序原理：对 DAG 的顶点进行排序，使得对每一条有向边 （u, v），均有 u（在排序记录中）比 v 先出现。
     * <p>
     * 相关题目：
     * {@link CourseScheduleII}
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度表
        int[] inDegree = new int[numCourses];
        // 邻接表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            List<Integer> list = map.getOrDefault(prerequisite[1], new ArrayList<>());
            list.add(prerequisite[0]);
            map.put(prerequisite[1], list);
        }

        Queue<Integer> queue = new LinkedList<>();
        // 入列所有入度为 0 的课
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 选课数
        int course = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            course++;
            if (map.containsKey(poll)) {
                for (Integer num : map.get(poll)) {
                    if (--inDegree[num] == 0) {
                        queue.offer(num);
                    }
                }
            }
        }

        return course == numCourses;
    }


    /**
     * 思路：通过 DFS 判断图中是否有环。借助一个标志列表 flags，用于判断每个节点 i 的状态：
     * 1. 未被 DFS 访问：i == 0；
     * 2. 已被其他节点启动的 DFS 访问：i == -1；
     * 3. 已被当前节点启动的 DFS 访问：i == 1。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        // 说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即课程安排图有环
        if (flags[i] == 1) {
            return false;
        }
        // 说明当前访问节点已被其他节点启动的 DFS 访问，路径不存在环
        if (flags[i] == -1) {
            return true;
        }
        flags[i] = 1;

        for (int j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        // 标记该路径不存在环
        flags[i] = -1;
        return true;
    }

}
