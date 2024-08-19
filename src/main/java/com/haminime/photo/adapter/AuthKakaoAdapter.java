package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.domain.entity.PlatformUser;

public interface AuthKakaoAdapter {
    Void loginRequest();
    String getAccessToken(String code);
    PlatformUser getInfo(String accessToken);
    long searchUserIdById(String id);
    Void registUser(String id, long userId);
}
