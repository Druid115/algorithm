/**
 * @Author: Druid
 * @Date: 2023/5/24 16:08
 * @Description: 28. 找出字符串中第一个匹配项的下标
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {

    /**
     * 思路：KMP 算法。
     */
    public int strStr(String haystack, String needle) {
        char[] arr1 = haystack.toCharArray();
        char[] arr2 = needle.toCharArray();
        int[] next = generateNextArr(arr2);
        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = next[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (j == arr2.length) {
            return i - j;
        }

        return -1;
    }

    public int[] generateNextArr(char[] arr) {
        int[] next = new int[arr.length];

        int i = 0;
        int j = 1;
        while (j < arr.length) {
            if (arr[j] == arr[i]) {
                next[j] = i + 1;
                i++;
                j++;
            } else {
                if (i != 0) {
                    i = next[i - 1];
                } else {
                    next[j] = 0;
                    j++;
                }
            }
        }

        return next;
    }
}