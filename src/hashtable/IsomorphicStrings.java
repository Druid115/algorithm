package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/6/21 17:27
 * @Description: 205. 同构字符串
 */
public class IsomorphicStrings {

    /**
     * 思路：维护两张 hash 表，分别记录 s -> t 的字符映射和 t -> s 的字符映射。
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (!sMap.getOrDefault(sChar, tChar).equals(tChar) ||
                    !tMap.getOrDefault(tChar, sChar).equals(sChar)) {
                return false;
            }

            sMap.put(sChar, tChar);
            tMap.put(tChar, sChar);
        }
        return true;
    }

    /**
     * 思路：对比字符第一次映射的下标。
     */
    public boolean isIsomorphic2(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
