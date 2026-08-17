package com.virtualcard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {

    private final StringRedisTemplate redis;

    private static final String KEY = "rate-limit:global";

    @Value("${app.rate-limit.requests:100}")
    private long limit;

    @Value("${app.rate-limit.window-seconds:60}")
    private long windowSeconds;

    public RateLimitService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public boolean validate() {
        Long count = redis.opsForValue().increment(KEY);

        if (count != null && count == 1) {
            redis.expire(
                    KEY,
                    Duration.ofSeconds(windowSeconds)
            );
        }

        return count != null && count <= limit;
    }
}
