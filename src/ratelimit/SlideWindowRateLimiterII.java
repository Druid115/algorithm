package ratelimit;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: DingRD
 * @Date: 2024/11/21 10:02
 * @Description: 滑动窗口限流
 */
public class SlideWindowRateLimiterII {

    /**
     * 单位时间划分的小周期（单位时间是 1 分钟）
     */
    private final int subCycle;

    /**
     * 限流数量
     */
    private final int limit;

    /**
     * 计数器, k - 当前窗口的开始时间值秒，value - 当前窗口的计数
     */
    private final Map<Long, Integer> counters;

    public SlideWindowRateLimiterII(int windowSize, int limit) {
        this.subCycle = windowSize;
        this.limit = limit;
        this.counters = new TreeMap<>();
    }

    public synchronized boolean tryAcquire() {
        long current = System.currentTimeMillis();
        // 获取当前时间在哪个小周期窗口
        long currentWindowTime = current / subCycle * subCycle;

        int currentWindowNum = slideWindow(currentWindowTime);
        // 超过阀值限流
        if (currentWindowNum >= limit) {
            return false;
        }

        // 计数器
        counters.merge(currentWindowTime, 1, Integer::sum);
        return true;
    }

    private int slideWindow(long currentWindowTime) {
        // 计算窗口开始位置
        long startTime = currentWindowTime - (long) subCycle * (60 / subCycle - 1);
        int count = 0;

        //遍历存储的计数器
        Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            // 删除无效过期的子窗口计数器
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                // 累加当前窗口的所有计数器之和
                count = count + entry.getValue();
            }
        }
        return count;
    }
}
