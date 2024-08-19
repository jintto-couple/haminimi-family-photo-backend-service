package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.service.AuthKakaoService;
import org.springframework.stereotype.Component;

@Component
public class AuthKakaoAdapterImpl implements AuthKakaoAdapter {

    private AuthKakaoService authKakaoService;

    @Override
    public Void loginRequest() {
        authKakaoService.tryLogin();
        return null;
    }

    @Override
    public String getAccessToken(String code) {
        return authKakaoService.getToken(code);
    }

    @Override
    public PlatformUser getInfo(String accessToken) {
        return authKakaoService.getInfo(accessToken);
    }

    @Override
    public long searchUserIdById(String id) {
        return authKakaoService.searchUser(Long.parseLong(id));
    }

    @Override
    public Void registUser(String id, long userId) {
        authKakaoService.registUser(Long.parseLong(id), userId);
        return null;
    }

}
