package com.haminime.photo.adapter;

import com.haminime.photo.service.dto.PlatformUser;

public interface AuthPlatformAdapter {
    String loginRequest();
    String getAccessToken(String code);
    PlatformUser getInfo(String accessToken);
    long searchUserIdById(String id);
    void createUser(String id, long userId);
}
