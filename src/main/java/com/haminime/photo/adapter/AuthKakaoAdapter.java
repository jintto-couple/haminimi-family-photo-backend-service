package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.KakaoUser;

public interface AuthKakaoAdapter {
    void loginRequest();
    long searchUser(long id);
    KakaoUser getUser(long userId);
    void registUser(long id, long userId);
    void deleteUser(long userId);
}
