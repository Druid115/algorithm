package sensitivewordfiltering;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: DingRD
 * @Date: 2024/10/31 16:01
 * @Description:
 */
public class TireNode {
    Map<Character, TireNode> children;

    boolean isEndOfWord;

    public TireNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}
