package com.haminime.photo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private final String key = "Haminimi-2024-Backend-SpringBoot-SecretKey";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

    public String createToken(Map<String, Object> tokenData) {
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1시간
        JwtBuilder builder = Jwts.builder().header().add("typ", "JWT").and();
        for(String key : tokenData.keySet()){
            builder.claim(key, tokenData.get(key));
        }
        String token = builder.expiration(exp).signWith(secretKey).compact();

        return token;
    }

    public Jws<Claims> validate(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

}
