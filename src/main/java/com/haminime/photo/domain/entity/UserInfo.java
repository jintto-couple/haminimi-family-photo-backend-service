package com.haminime.photo.domain.entity;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class UserInfo {
    private String userName;
    private int userNo;
    private String profileUrl;
    private String registDate;
    private String updateDate;
    private boolean isDelete;

    @Value("${profile.defaultUrl}")
    private static final String defaultUrl;

    public static UserInfo createInstance(int userNo) {
        return UserInfo.builder().userNo(userNo).profileUrl(defaultUrl).registDate(LocalDateTime.now().toString()).updateDate(LocalDateTime.now().toString()).isDelete(false).build();
    }
}
