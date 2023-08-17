package graph;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/8/11 17:31
 * @Description: 433. 最小基因变化
 */
public class MinimumGeneticMutation {

    /**
     * 思路：广度优先遍历。起始将 startGene 加入队列，并更新到达 startGene 所使用的步数为 0。然后进行 BFS：
     * 每次取出队头元素，尝试替换当前状态的某一位，来得到新的状态（新状态必须合法），如果新状态合法并且没有在记录步数的哈希表中出现过，则将新状态入队并更新得到新状态所用步数，否则丢弃新状态。
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) {
            return 0;
        }
        char[] letters = {'A', 'T', 'C', 'G'};
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(endGene)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        // 存储当前值，以及当前 step
        Map<String, Integer> map = new HashMap<>();
        queue.offer(startGene);
        map.put(startGene, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                String cur = queue.poll();
                Integer step = map.get(cur);
                char[] cs = cur.toCharArray();

                for (int i = 0; i < cs.length; i++) {
                    for (char c : letters) {
                        // 拷贝字符串，保证原字符串不受影响
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String s = new String(clone);

                        if (s.equals(endGene)) {
                            return step + 1;
                        }
                        if (set.contains(s) && !map.containsKey(s)) {
                            map.put(s, step + 1);
                            queue.offer(s);
                        }
                    }
                }
                size--;
            }
        }
        return -1;
    }
}
