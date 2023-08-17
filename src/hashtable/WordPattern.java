package hashtable;

import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/6/25 9:54
 * @Description: 290. 单词规律
 */
public class WordPattern {

    /**
     * 思路：使用 map，如果 key 不存在，插入成功，返回 null；如果 key 存在，返回之前对应的 value。
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            // 注意避开 [-128， 127] 的限制，要么就将变量 i 的类型设置为 Integer 进行主动装箱
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }
}
