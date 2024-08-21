package com.haminime.photo.util;

import com.haminime.photo.repository.RefreshTokenRedisRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RefreshTokenRedisUtil {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    public String save(long value) {
        String key = UUID.randomUUID().toString();
        refreshTokenRedisRepository.save(key, value, 30, TimeUnit.DAYS);
        return key;
    }

    public long searchValueByKey(String key) {
        return refreshTokenRedisRepository.find(key);
    }

    public long checkExpire(String key) {
        return refreshTokenRedisRepository.getExpire(key);
    }

    public void deleteByKey(String key) {
        refreshTokenRedisRepository.delete(key);
    }

    public String createNewDataByKey(String key) {
        String newKey = save(searchValueByKey(key));
        deleteByKey(key);
        return newKey;
    }

}