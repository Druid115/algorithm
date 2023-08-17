package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/6/25 11:00
 * @Description: 49. 字母异位词分组
 */
public class GroupAnagrams {

    /**
     * 思路：遍历数组中的每个字符串，用数组记录该字符串中每个字符出现的次数，再按照顺序拼接这些字符并放入到 map 中。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] arr = new int[26];

            for (int i = 0; i < s.length(); i++) {
                arr[s.charAt(i) - 'a']++;
            }

            // 统一了 map 中 key 的字符顺序
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                while (arr[i] > 0) {
                    sb.append((char) ('a' + i));
                    arr[i]--;
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }
}
