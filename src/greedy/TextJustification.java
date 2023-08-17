package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/6/8 16:15
 * @Description: 68. 文本左右对齐
 */
public class TextJustification {

    /**
     * 思路：如果当前行只有一个单词或者当前行为最后一行，特殊处理为左对齐；
     * 其余为一般情况，分别计算「当前行单词总长度」、「均分的空格长度」和「需要增加多余的空格的单词长度」，然后依次进行拼接。
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int idx = 0;
        while (idx < words.length) {
            List<String> list = new ArrayList<>();
            int curLength = 0;
            // 为了保证每个单词末尾都有一个空格，对 maxWidth 进行加 1，一般化处理
            while (idx < words.length && curLength + words[idx].length() + 1 <= maxWidth + 1) {
                curLength = curLength + words[idx].length() + 1;
                list.add(words[idx]);
                idx++;
            }

            StringBuilder sb = new StringBuilder();
            int size = list.size();
            if (idx == words.length || size == 1) {
                for (String s : list) {
                    sb.append(s).append(" ");
                }

                // 判断需不需要追加空格
                if (curLength == maxWidth + 1) {
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                } else {
                    int len = maxWidth - sb.length();
                    while (len > 0) {
                        sb.append(" ");
                        len--;
                    }
                    res.add(sb.toString());
                }
            } else {
                // 需要追加空格的数量，maxWidth + 1 来抵消最后一个空格
                int len = maxWidth + 1 - curLength;
                // 除掉末尾单词，其余每个单词需要追加的空格数量
                int spaceWidth = len / (size - 1);
                // [0, spaceItem) 个单词需要追加多余的空格
                int spaceItem = len % (size - 1);

                StringBuilder completion = new StringBuilder();
                while (spaceWidth > 0) {
                    completion.append(" ");
                    spaceWidth--;
                }

                for (int i = 0; i < size; i++) {
                    sb.append(list.get(i));
                    // 最后一个单词不需要追加空格
                    if (i == size - 1) {
                        break;
                    }
                    sb.append(" ");
                    sb.append(completion);
                    if (i < spaceItem) {
                        sb.append(" ");
                    }
                }
                res.add(sb.toString());
            }
        }
        return res;
    }
}
