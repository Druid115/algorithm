package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2023/7/6 10:44
 * @Description: 146. LRU 缓存
 */
public class LRUCache {

    private int size;
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    static class Node {
        int key;
        int val;
        Node next;
        Node pre;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * 思路：双向链表 + Map，将最近使用的元素存储到链表头节点，容量达到最大时，删除链表末尾的元素。
     */
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            Node node = new Node(value);
            node.key = key;
            addToHead(node);
            map.put(key, node);
            if (size == capacity) {
                removeTail();
            } else {
                size++;
            }
        }
    }

    private void moveToHead(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    private void removeTail() {
        Node lastNode = tail.pre;
        lastNode.pre.next = tail;
        tail.pre = lastNode.pre;
        map.remove(lastNode.key);
        lastNode = null;
    }

}
