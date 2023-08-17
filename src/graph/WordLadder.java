package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/14 10:00
 * @Description: 127. 单词接龙
 */
public class WordLadder {

    /**
     * 思路：问题可转换为求无向图中两个顶点之间的最短路径的长度。
     * <p>
     * 相关题目：
     * {@link MinimumGeneticMutation}
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        // 存储当前值，以及当前 step
        Map<String, Integer> map = new HashMap<>();
        queue.offer(beginWord);
        map.put(beginWord, 1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                String cur = queue.poll();
                Integer step = map.get(cur);
                char[] cs = cur.toCharArray();

                for (int i = 0; i < cs.length; i++) {
                    // 先保存，然后恢复
                    char originChar = cs[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        cs[i] = j;
                        String s = new String(cs);

                        if (s.equals(endWord)) {
                            return step + 1;
                        }
                        if (set.contains(s) && !map.containsKey(s)) {
                            map.put(s, step + 1);
                            queue.offer(s);
                        }
                    }
                    // 恢复
                    cs[i] = originChar;
                }
                size--;
            }
        }
        return 0;
    }

    /**
     * 思路：双向广度优先遍历。 BFS 随着层数的加深，遍历的增速越快，导致搜索空间爆炸的问题。
     * 同时从两个方向开始搜索，一旦搜索到相同的值，意味着找到了一条联通起点和终点的最短路径。
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        // 正向搜索
        Queue<String> beginQueue = new LinkedList<>();
        // 反向搜索
        Queue<String> endQueue = new LinkedList<>();
        // 正向 map
        Map<String, Integer> beginMap = new HashMap<>();
        // 反向 map
        Map<String, Integer> endMap = new HashMap<>();
        beginQueue.offer(beginWord);
        endQueue.offer(endWord);
        beginMap.put(beginWord, 1);
        endMap.put(endWord, 1);

        // 只有两个队列都不为空，才有必要继续搜索下去。只要其中一个队列为空，说明搜不到该方向的目标节点
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            int res;
            // 优先从队列元素少的方向开始搜索
            if (beginQueue.size() <= endQueue.size()) {
                res = canConvert(beginQueue, beginMap, endMap, set);
            } else {
                res = canConvert(endQueue, endMap, beginMap, set);
            }
            if (res != -1) {
                return res;
            }
        }
        return 0;
    }

    public int canConvert(Queue<String> queue, Map<String, Integer> cur, Map<String, Integer> other, Set<String> set) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                String poll = queue.poll();
                Integer step = cur.get(poll);
                char[] charArray = poll.toCharArray();

                for (int i = 0; i < charArray.length; i++) {
                    // 先保存，然后恢复
                    char originChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        charArray[i] = j;
                        String s = new String(charArray);

                        if (set.contains(s)) {
                            // 如果该字符在另外一个方向中搜索过，说明找到了两个方向的连接点
                            if (other.containsKey(s)) {
                                return step + other.get(s);
                            }

                            if (!cur.containsKey(s)) {
                                cur.put(s, step + 1);
                                queue.offer(s);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
                size--;
            }
        }
        return -1;
    }
}
