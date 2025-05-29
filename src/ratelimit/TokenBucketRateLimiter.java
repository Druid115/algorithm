package ratelimit;

/**
 * @Author: DingRD
 * @Date: 2024/11/21 11:39
 * @Description: 令牌桶限流
 */
public class TokenBucketRateLimiter {

    /**
     * 令牌桶容量
     */
    private final int capacity;

    /**
     * 令牌生成速率，单位：令牌/秒
     */
    private final int rate;

    /**
     * 当前令牌数量
     */
    private int tokens;

    /**
     * 上次令牌生成时间戳
     */
    private long lastRefillTimestamp;


    public TokenBucketRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = 0;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long gapTime = now - lastRefillTimestamp;
        // 请求时间相距最后令牌产生时间需要大于等于 1s（因为生成令牌的最小时间单位是 1s）
        if (gapTime >= 1000) {
            int generatedTokens = (int) (gapTime * rate) / 1000;
            tokens = Math.min(tokens + generatedTokens, capacity);
            lastRefillTimestamp = now;
        }

        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    private String lua = "" +
            "local key = KEYS[1]" +
            "local capacity = tonumber(ARGV[1])" +
            "local rate = tonumber(ARGV[2])" +
            "local now = tonumber(ARGV[3])" +
            "" +
            "local bucket = redis.call('HMGET', key, 'tokens', 'lastRefillTime')" +
            "local tokens = tonumber(bucket[1]) or capacity" +
            "local lastRefillTime = tonumber(bucket[2]) or now" +
            "" +
            "local delta = math.max(0, now - lastRefillTime)" +
            "local newTokens = delta * rate" +
            "tokens = math.min(capacity, tokens + newTokens)" +
            "" +
            "if tokens < 1 then" +
            "  return 0" +
            "else" +
            "  tokens = tokens - 1" +
            "  redis.call('HMSET', key, tokens, 'lastRefillTime', now)" +
            "  redis.call('EXPIRE', key, math.ceil(capacity / rate) * 2)" +
            "  return 1" +
            "end";
}
