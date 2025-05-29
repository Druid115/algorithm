package ratelimit;

/**
 * @Author: DingRD
 * @Date: 2024/11/21 11:21
 * @Description: 漏桶限流
 */
public class LeakyBucketRateLimiter {

    /**
     * 桶的容量
     */
    private final int capacity;

    /**
     * 漏桶出水速率（每秒）
     */
    private final int rate;

    /**
     * 当前桶中的水量
     */
    private long water;

    /**
     * 上次漏水时间戳
     */
    private long lastLeakTimestamp;

    public LeakyBucketRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastLeakTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long timeGap = now - lastLeakTimestamp;
        long leakedWater = timeGap * rate / 1000;
        water = Math.max(0, water - leakedWater);
        lastLeakTimestamp = now;

        if (water < capacity) {
            water += 1;
            return true;
        }
        return false;
    }

    private String lua = "" +
            "local key = KEYS[1]" +
            "local capacity = tonumber(ARGV[1])" +
            "local rate = tonumber(ARGV[2])" +
            "local now = tonumber(ARGV[3])" +
            "" +
            "local bucket = redis.call('HMGET', key, 'water', 'lastLeakTime')" +
            "local water = tonumber(bucker[1]) or 0" +
            "local lastLeakTime = tonumber(bucker[2]) or now" +
            "" +
            "local delta = math.min(now - lastLeakTime)" +
            "local leaked = delta * rate" +
            "water = math.max(0, water - leaked)" +
            "" +
            "if water >= capacity then" +
            "  return 0" +
            "else" +
            "  redis.call('HMSET', key, 'water', water, 'lastLeakTime', lastLeakTime)" +
            "  redis.call('EXPIRE', key, math.ceil(capacity / rate) * 2)" +
            "  return 1" +
            "end";
}
