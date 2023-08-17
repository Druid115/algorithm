package hashtable;

/**
 * @Author: Druid
 * @Date: 2023/6/25 10:37
 * @Description: 242. 有效的字母异位词
 */
public class ValidAnagram {

    /**
     * 思路：使用数组记录字符串 s 中每个字符出现的次数，遍历字符串 t 的每个字符，判断数组中该字符的次数是否大于 0。
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int[] arr = new int[26];
        for (char c : sCharArray) {
            arr[c - 'a']++;
        }
        for (char c : tCharArray) {
            if (arr[c - 'a'] == 0) {
                return false;
            }
            arr[c - 'a']--;
        }
        return true;
    }
}
