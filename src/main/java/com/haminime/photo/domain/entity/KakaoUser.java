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
    private String kakaoId;
    private String registDate;

    public static KakaoUser createInstance(String id, int userNo) {
        return KakaoUser.builder().kakaoId(id).userNo(userNo).registDate(LocalDateTime.now().toString()).build();
    }

}
