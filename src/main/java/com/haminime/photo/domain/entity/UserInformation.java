package com.haminime.photo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

    @Id
    private long userId;
    private String userName;
    private String profileUrl;
    private String registDate;
    private String updateDate;
    private boolean isDelete;

    public static UserInformation createInstance(long userId, String userName, String defaultUrl) {
        return UserInformation.builder().userId(userId).userName(userName).profileUrl(defaultUrl).registDate(LocalDateTime.now().toString()).updateDate(LocalDateTime.now().toString()).isDelete(false).build();
    }
}
