package com.haminime.photo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRedisRepository {

    private final RedisTemplate<String, Long> redisTemplate;

    public void save(String key, Long value, long timeout, TimeUnit unit) {
        ValueOperations<String, Long> ops = redisTemplate.opsForValue();
        ops.set(key, value, timeout, unit);
    }

    public Long find(String key) {
        ValueOperations<String, Long> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}
