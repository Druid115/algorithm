package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: DingRD
 * @Date: 2024/11/20 15:12
 * @Description:
 */
public class Test {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的
     * 异位词(字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。) 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *  
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *  示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *  
     * 提示:
     * * 1 <= s.length, p.length <= 3 * 10^4
     * * s 和 p 仅包含小写字母
     */
    public static List<Integer> getIndex(String s, String p) {
        char[] charArray = p.toCharArray();
        int count = charArray.length;
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        for(char c: charArray) {
            int max = Math.max(arr[c - 'a'], 0);
            arr[c - 'a'] = ++max;
        }
        List<Integer> res = new ArrayList<>();
        char[] sCharArray = s.toCharArray();
        int left = 0;
        int right = 0;
        while (right < sCharArray.length) {
            char c = sCharArray[right];
            if (arr[c - 'a'] > 0) {
                arr[c - 'a']--;
                if (--count == 0) {
                    res.add(left);
                    arr[sCharArray[left] - 'a']++;
                    left++;
                    count++;
                }
                right++;
            } else if (arr[c - 'a'] == -1) {
                while (left < right) {
                    arr[sCharArray[left] - 'a']++;
                    left++;
                    count++;
                }
                left++;
                right++;
            } else {
                while (left < right && arr[c - 'a'] == 0) {
                    arr[sCharArray[left] - 'a']++;
                    left++;
                    count++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> index = getIndex("abab", "ab");
        System.out.println(index);
    }
}
