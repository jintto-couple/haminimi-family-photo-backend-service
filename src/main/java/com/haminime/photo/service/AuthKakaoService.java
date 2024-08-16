package com.haminime.photo.service;

import com.haminime.photo.client.KakaoClient;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.KakaoTokenInfoResponse;
import com.haminime.photo.controller.dto.response.KakaoTokenResponse;
import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.repository.KakaoLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthKakaoService implements AuthPlatformService{

    private final KakaoClient client;
    private final KakaoLoginRepository kakaoLoginRepository;

    @Value("${spring.kakao.restapiKey}")
    private String restapiKey;
    @Value("${spring.kakao.redirectUrl}")
    private String redirectUrl;

    private void tryLogin(){
        client.tryLoginRequest();
    }

    private KakaoUser registUser(long id, long userId) {
        try {
            KakaoUser kakaoUser = KakaoUser.createInstance(id, userId);
            return kakaoLoginRepository.save(kakaoUser);
        } catch (Exception e) {
            throw new CommonException();
        }
    }


    private KakaoTokenInfoResponse getInfo(final String code) {
        final KakaoTokenResponse token = getToken(code);
        try {
            return client.getInfo(token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            throw new CommonException();
        }
    }

    private KakaoTokenResponse getToken(final String code) {
        try {
            return client.getToken(restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            throw new CommonException();
        }
    }

}
