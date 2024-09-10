package binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Druid
 * @Date: 2024/1/4 10:06
 * @Description: 981. 基于时间的键值存储
 */
public class TimeBasedKeyValueStore {

    static class TimeMap {

        static class Node {
            String key;
            String val;
            int time;

            Node(String key, String val, int time) {
                this.key = key;
                this.val = val;
                this.time = time;
            }
        }

        private final Map<String, List<Node>> map;

        /**
         * 思路：HashMap + 二分。
         */
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            list.add(new Node(key, value, timestamp));
            map.put(key, list);
        }

        public String get(String key, int timestamp) {
            List<Node> list = map.getOrDefault(key, new ArrayList<>());
            if (list.isEmpty() || list.get(0).time > timestamp) {
                return "";
            }
            int size = list.size();
            int left = 0;
            int right = size - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                Node node = list.get(mid);
                if (node.time == timestamp) {
                    return node.val;
                } else if (node.time < timestamp) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return list.get(right).val;
        }
    }
}
