package ratelimit;

/**
 * @Author: DingRD
 * @Date: 2024/11/19 15:06
 * @Description: 固定窗口限流
 */
public class FixedWindowRateLimiter {

    /**
     * 窗口大小
     */
    private final long windowSize;

    /**
     * 限流数量
     */
    private final int limit;

    /**
     * 窗口右边界
     */
    private long windowEndTime;

    /**
     * 计数器
     */
    private int counter;


    public FixedWindowRateLimiter(long windowSize, int limit) {
        this.windowSize = windowSize;
        this.limit = limit;
        this.windowEndTime = System.currentTimeMillis() + windowSize;
        this.counter = 0;
    }

    public synchronized boolean tryAcquire() {
        long current = System.currentTimeMillis();
        while (current > windowEndTime) {
            windowEndTime += windowSize;
            counter = 0;
        }
        return ++counter <= limit;
    }

    private String lua = "" +
            "local key = KEYS[1]" +
            "local window = tonumber(ARGV[1])" +
            "local macCount = tonumber(ARGV[2])" +
            "" +
            "local current = redis.call('GET', key)" +
            "current = tonumber(current) or 0" +
            "" +
            "if current >= maxCount then" +
            "  return 0" +
            "else" +
            "  redis.call('INCR', key)" +
            "  if current == 0 then" +
            "    redis.call('EXPIRE', key, window)" +
            "  end" +
            "  return 1" +
            "end";

}
