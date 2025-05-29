package ratelimit;

import java.util.LinkedList;

/**
 * @Author: DingRD
 * @Date: 2024/11/19 15:34
 * @Description: 滑动窗口限流
 */
public class SlideWindowRateLimiter {

    /**
     * 窗口数
     */
    private final int windowNum;

    /**
     * 每个窗口大小
     */
    private final long windowSize;

    /**
     * 窗口限流数量
     */
    private final int limit;

    /**
     * 窗口队列
     */
    private final LinkedList<Integer> slots;

    /**
     * 计数器
     */
    private int count;


    public SlideWindowRateLimiter(int windowNum, long windowSize, int limit) {
        this.windowNum = windowNum;
        this.windowSize = windowSize;
        this.limit = limit;
        this.slots = new LinkedList<>();
        this.count = 0;

        slots.offer(0);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(this.windowSize);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (slots) {
                    slots.offer(0);
                    if (slots.size() > this.windowNum) {
                        synchronized (this) {
                            count -= slots.getFirst();
                        }
                        slots.poll();
                    }
                }
            }
        }).start();
    }

    public synchronized boolean tryAcquire() {
        if ((count + 1) > limit) {
            return false;
        }
        synchronized (slots) {
            slots.set(slots.size() - 1, slots.getLast() + 1);
        }
        count++;
        return true;
    }

    private String lua = "" +
            "local key = KEYS[1]" +
            "local window = tonumber(ARGV[1]) * 1000" +
            "local maxCount = tonumber(ARGV[2])" +
            "local now = tonumber(ARGV[3])" +
            "" +
            "redis.call('ZREMRANGEBYSCORE', key, 0, now - window)" +
            "" +
            "local count = redis.call('ZCARD', key)" +
            "" +
            "if count >= maxCount then" +
            "  return 0" +
            "else" +
            "  redis.call('ZADD', key, now, now)" +
            "  redis.call('EXPIRE', key, window / 1000)" +
            "  return 1" +
            "end";
}
