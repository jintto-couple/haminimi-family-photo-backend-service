package com.haminime.photo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoUser extends PlatformUser {

    @Id
    private long kakaoId;
    private long userId;

    public static KakaoUser createInstance(long id, long userId) {
        return KakaoUser.builder().kakaoId(id).userId(userId).build();
    }

}
