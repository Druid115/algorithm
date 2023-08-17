package trie;

/**
 * @Author: Druid
 * @Date: 2023/8/14 15:12
 * @Description: 211. 添加与搜索单词 - 数据结构设计
 */
public class WordDictionary {

    static class TrieNode {
        boolean end;

        TrieNode[] next = new TrieNode[26];
    }

    private TrieNode root;

    /**
     * 思路：构造字典树，搜索时需要枚举每个 . 指代的字母，因此要结合 DFS 来做。
     */
    public WordDictionary() {
        this.root = new TrieNode();
        root.end = false;
    }

    public void addWord(String word) {
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new TrieNode();
            }
            cur = cur.next[idx];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int idx) {
        if (idx == word.length()) {
            return node.end;
        }

        char c = word.charAt(idx);
        if (c == '.') {
            // 遍历当前层的所有节点
            for (TrieNode next : node.next) {
                if (next != null && search(next, word, idx + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int i = c - 'a';
            if (node.next[i] == null) {
                return false;
            }
            return search(node.next[i], word, idx + 1);
        }
    }
}
