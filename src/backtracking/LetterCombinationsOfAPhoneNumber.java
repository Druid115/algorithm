package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Druid
 * @Date: 2023/8/16 10:20
 * @Description: 17. 电话号码的字母组合
 */
public class LetterCombinationsOfAPhoneNumber {

    private final String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 思路：深度优先遍历的回溯。
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        dfs(digits.toCharArray(), 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(char[] digitsArray, int idx, StringBuilder sb, List<String> res) {
        if (idx == digitsArray.length) {
            res.add(sb.toString());
            return;
        }
        String letter = letters[digitsArray[idx] - '0'];
        for (int i = 0; i < letter.length(); i++) {
            sb.append(letter.charAt(i));
            dfs(digitsArray, idx + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 思路：广度优先遍历的回溯。
     */
    public List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        // 占位
        queue.offer("");
        for (int i = 0; i < digits.length(); i++) {
            String letter = letters[digits.charAt(i) - '0'];
            int size = queue.size();
            while (size > 0) {
                // 每次从队列中取出一个元素，然后与新的字符串进行拼接并入队
                String poll = queue.poll();
                for (int j = 0; j < letter.length(); j++) {
                    queue.offer(poll + letter.charAt(j));
                }
                size--;
            }
        }
        res.addAll(queue);
        return res;
    }
}
