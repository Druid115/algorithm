package trie;

/**
 * @Author: Druid
 * @Date: 2023/8/14 14:14
 * @Description: 208. 实现 Trie (前缀树)
 */
public class Trie {

    static class TrieNode {
        boolean end;
        TrieNode[] next = new TrieNode[26];
    }

    private TrieNode root;

    /**
     * 思路：构造一棵多叉树，根节点到每个叶节点的路径对应一个完整的单词。
     * <p>
     * 相关题目：
     * {@link WordDictionary}
     */
    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
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
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                return false;
            }
            cur = cur.next[idx];
        }
        return cur.end;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = this.root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (cur.next[idx] == null) {
                return false;
            }
            cur = cur.next[idx];
        }
        return true;
    }
}
