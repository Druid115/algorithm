import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Druid
 * @Date: 2023/5/17 18:28
 * @Description: 6. N 字形变换
 */
public class ZigzagConversion {

    /**
     * 思路：找到各个字母转换后的间隔，有如下规律：
     * step = 2 * numRows - 2
     * 第 0 层和第 numRows - 1 层的下标间距总是 step。
     * 中间层的下标间距总是 step - 2 * 行数，2 * 行数 交替。
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        int step = numRows + numRows - 2;
        int interval;

        int i = 0;
        while (i < numRows) {
            int idx = i;
            interval = i * 2;
            while (idx < charArray.length) {
                sb.append(charArray[idx]);
                // 达到交替的目的
                interval = step - interval;
                idx += (i == 0 || i == numRows - 1) ? step : interval;
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * 思路：模拟遍历。
     */
    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        // 当到达两端时，对 flag 取相反数，可以控制方向
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
