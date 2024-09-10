package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/9 16:41
 * @Description: 210. 课程表 II
 */
public class CourseScheduleII {

    /**
     * 思路：拓扑排序。
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] courseArr = new int[numCourses];
        // 入度表
        int[] inDegree = new int[numCourses];
        // 邻接表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] cp : prerequisites) {
            inDegree[cp[0]]++;
            List<Integer> list = map.getOrDefault(cp[1], new ArrayList<>());
            list.add(cp[0]);
            map.put(cp[1], list);
        }

        Queue<Integer> queue = new LinkedList<>();
        // 入列所有入度为 0 的课
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 选课数
        int course = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            courseArr[course++] = poll;
            if (map.containsKey(poll)) {
                for (Integer num : map.get(poll)) {
                    if (--inDegree[num] == 0) {
                        queue.offer(num);
                    }
                }
            }
        }

        if (course == numCourses) {
            return courseArr;
        }
        return new int[0];
    }

    /**
     * 思路：通过 DFS 判断图中是否有环。
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] courseArr = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i, stack)) {
                return new int[0];
            }
        }

        int idx = 0;
        while (!stack.isEmpty()) {
            courseArr[idx++] = stack.pop();
        }
        return courseArr;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i, Deque<Integer> stack) {
        if (flags[i] == 1) {
            return false;
        }
        if (flags[i] == -1) {
            return true;
        }
        flags[i] = 1;
        for (int j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j, stack)) {
                return false;
            }
        }
        flags[i] = -1;
        stack.push(i);
        return true;
    }
}
