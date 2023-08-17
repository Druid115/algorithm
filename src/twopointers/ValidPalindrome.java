package twopointers;

/**
 * @Author: Druid
 * @Date: 2023/5/19 18:16
 * @Description: 125. 验证回文串
 */
public class ValidPalindrome {

    /**
     * 思路：双指针
     */
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }

        char[] charArray = s.toLowerCase().toCharArray();
        int left = 0, right = charArray.length - 1;
        while (left < right) {
            while (left < right && !isValid(charArray[left])) {
                left++;
            }

            while (left < right && !isValid(charArray[right])) {
                right--;
            }

            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
