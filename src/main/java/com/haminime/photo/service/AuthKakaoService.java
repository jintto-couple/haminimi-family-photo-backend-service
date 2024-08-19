package com.haminime.photo.service;

import com.haminime.photo.client.KakaoClient;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.KakaoTokenInfoResponse;
import com.haminime.photo.controller.dto.response.KakaoTokenResponse;
import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.repository.KakaoLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthKakaoService {

    private final KakaoClient client;
    private final KakaoLoginRepository kakaoLoginRepository;

    @Value("${oauth.kakao.restapiKey}")
    private String restapiKey;
    @Value("${domain}")
    private String domain;
    private String callback = "/oauth2/callback/login/kakao";

    public void tryLogin(){
        client.tryLoginRequest();
    }

    public String getToken(String code) {
        Map<String, Object> tokenData = client.getToken(restapiKey, domain + callback, code, "authorization_code");
        if(tokenData.containsKey("access_token")){
            return tokenData.get("access_token").toString();
        }
        throw new CommonException();
    }

    public PlatformUser getInfo(String token){
        Map<String, Object> tokenData = client.getInfo("Bearer " + token, "application/x-www-form-urlencoded;charset=utf-8");
        if(tokenData.containsKey("kakao_account")){
            Map<String, Object> kakaoAccount = (Map<String, Object>) tokenData.get("kakao_account");
            Map<String, Object> userInfo = (Map<String, Object>) kakaoAccount.get("profile");
            return new PlatformUser(tokenData.get("id").toString(), userInfo.get("nickname").toString());
        }
        log.error("Kakao account not found");
        throw new CommonException();
    }

    public long searchUser(long id) {
        return kakaoLoginRepository.findById(id).map(KakaoUser::getUserId).orElse(-1L);
    }

    public void registUser(long id, long userId) {
        KakaoUser kakaoUser = KakaoUser.createInstance(id, userId);
        kakaoLoginRepository.save(kakaoUser);
    }


}
