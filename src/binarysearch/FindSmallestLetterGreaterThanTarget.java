package binarysearch;

/**
 * @Author: Druid
 * @Date: 2023/12/27 14:44
 * @Description: 744. 寻找比目标字母大的最小字母
 */
public class FindSmallestLetterGreaterThanTarget {

    /**
     * 思路：二分查找。
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        if (letters[right] < target) {
            return letters[0];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int distance = letters[mid] - target;
            if (distance <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return letters[left];
    }
}
