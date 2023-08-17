import java.util.*;

/**
 * @Author: Druid
 * @Date: 2023/5/10 15:21
 * @Description: 380. O(1) 时间插入、删除和获取随机元素
 */
public class RandomizedSet {

    private Random random;

    private Map<Integer, Integer> map;

    private List<Integer> list;

    /**
     * 思路：使用 HashMap + ArrayList 的组合，其中 map 中的 key 为 val，value 为 val 在 list 中的下标。删除时，将 list 将末尾元素移动到待删除元素的下标处。
     */
    public RandomizedSet() {
        random = new Random();
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        Integer location = map.remove(val);
        // 将末尾元素移动到待删除元素的下标处
        Integer last = list.remove(list.size() - 1);
        if (last != val) {
            list.set(location, last);
            map.put(last, location);
        }
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
