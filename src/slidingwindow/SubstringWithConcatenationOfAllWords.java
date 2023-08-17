package slidingwindow;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/6/16 16:22
 * @Description: 30. 串联所有单词的子串
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * 思路：由于 words 数组中的单词长度是固定的，因此，可以对 s 进行单词切割并进行匹配。
     * 1. 使用 allWordsMap 存储 words 数组中每个单词出现的次数
     * 2. 划定遍历范围从 [0, 单词长度)，能涵盖所有的情况
     * 3. 使用滑动窗口，使用 curMap 存储窗口中每个单词出现的次数
     *  3.1 如果单词不在 allWordsMap 中，直接移动左边界到最新的位置
     *  3.2 如果单词出现的次数大于 allWordsMap 中的次数，需要更新左边界的位置直到次数等于 allWordsMap 中的次数，在此过程中需要更新 curMap
     *  3.3 如果完全匹配，则记录左边界的位置
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }

        Map<String, Integer> allWordsMap = new HashMap<>();
        for (String word : words) {
            allWordsMap.put(word, allWordsMap.getOrDefault(word, 0) + 1);
        }

        int length = s.length();
        int m = words.length;
        int n = words[0].length();

        for (int i = 0; i < n; i++) {
            Map<String, Integer> curMap = new HashMap<>();

            int left = i;
            int right = i;
            // 使用 count 来标识当前匹配的单词数量
            int count = 0;
            while (right + n <= length) {
                String cur = s.substring(right, right + n);
                curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);
                count++;

                // 3.1
                if (!allWordsMap.containsKey(cur)) {
                    curMap = new HashMap<>();
                    left = right + n;
                    right += n;
                    count = 0;
                    continue;
                }

                // 3.2
                if (allWordsMap.get(cur) < curMap.get(cur)) {
                    while (allWordsMap.get(cur) < curMap.get(cur)) {
                        String removeStr = s.substring(left, left + n);
                        curMap.put(removeStr, curMap.get(removeStr) - 1);
                        left += n;
                        count--;
                    }
                    right += n;
                    continue;
                }

                // 3.3
                if (count == m) {
                    res.add(left);
                    String leftStr = s.substring(left, left + n);
                    curMap.put(leftStr, curMap.get(leftStr) - 1);
                    left += n;
                    count--;
                }
                right += n;
            }
        }
        return res;
    }
}

