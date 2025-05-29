package sensitivewordfiltering;

import trie.Trie;

/**
 * @Author: DingRD
 * @Date: 2024/10/31 16:02
 * @Description:
 */
public class Tire {

    private TireNode root;

    public Tire() {
        root = new TireNode();
    }

    public void insert(String word) {
        TireNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TireNode());
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TireNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TireNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TireNode current, String word, int index) {
        if (index == word.length()) {
            // 检查是否是一个有效的单词
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            // 检查当前节点是否没有子节点
            return current.children.isEmpty();
        }

        char ch = word.charAt(index);
        TireNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDelete = deleteHelper(node, word, index + 1);

        if (shouldDelete) {
            current.children.remove(ch);
            return current.children.isEmpty() && !current.isEndOfWord;
        }
        return false;
    }
}
