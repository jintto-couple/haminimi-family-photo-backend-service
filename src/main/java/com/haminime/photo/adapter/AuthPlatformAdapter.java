package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.PlatformUser;

public interface AuthPlatformAdapter {
    void loginRequest();
    String getAccessToken(String code);
    PlatformUser getInfo(String accessToken);
    long searchUserIdById(String id);
    void registUser(String id, long userId);
}
