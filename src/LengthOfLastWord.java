/**
 * @Author: Druid
 * @Date: 2023/5/16 18:29
 * @Description: 58. 最后一个单词的长度
 */
public class LengthOfLastWord {

    /**
     * 思路：从后往前遍历。
     */
    public int lengthOfLastWord(String s) {
        char[] charArray = s.toCharArray();
        int index = charArray.length - 1;
        while (charArray[index] == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && charArray[index] != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }
}
