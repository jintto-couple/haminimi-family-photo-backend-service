package com.haminime.photo.adapter;

import com.haminime.photo.service.dto.PlatformUser;
import com.haminime.photo.service.AuthKakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthKakaoAdapterImpl implements AuthKakaoAdapter {

    private final AuthKakaoService authKakaoService;

    @Override
    public String loginRequest() {
        return authKakaoService.fetchLoginURL();
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
    public void createUser(String id, long userId) {
        authKakaoService.createUser(Long.parseLong(id), userId);
    }

}
