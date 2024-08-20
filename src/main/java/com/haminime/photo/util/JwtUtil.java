package com.haminime.photo.util;

import com.haminime.photo.service.dto.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private final String key = "Haminimi-2024-Backend-SpringBoot-SecretKey";
    private final SecretKey secretKey = Keys
            .hmacShaKeyFor(Base64.getEncoder()
            .encodeToString(key.getBytes(StandardCharsets.UTF_8))
            .getBytes(StandardCharsets.UTF_8));

    public String createToken(long id) {
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1시간
        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .and()
                .claim("id", id)
                .expiration(exp)
                .signWith(secretKey)
                .compact();

    }

    public Jws<Claims> validate(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    public UserAuthentication validAndGetUser(String token) {
        Jws<Claims> claims = validate(token);
        Map<String, Object> claimsMap = claims.getBody();
        return new UserAuthentication(Long.parseLong(claimsMap.get("id").toString()));
    }
}
