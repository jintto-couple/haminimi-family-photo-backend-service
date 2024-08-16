package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.service.AuthKakaoService;
import org.springframework.stereotype.Component;

@Component
public class AuthKakaoAdapterImpl implements AuthKakaoAdapter {

    private AuthKakaoService authKakaoService;

    @Override
    public void loginRequest() {
        authKakaoService.tryLogin();
    }

    @Override
    public long searchUser(long id) {
        return authKakaoService.searchUser(id);
    }

    @Override
    public KakaoUser getUser(long userId) {
        return authKakaoService.getUser(userId);
    }

    @Override
    public void registUser(long id, long userId) {
        authKakaoService.registUser(id, userId);
    }

    @Override
    public void deleteUser(long id) {
        authKakaoService.deleteKakaoUser(id);
    }
}
