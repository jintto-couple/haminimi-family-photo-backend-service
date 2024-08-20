package com.haminime.photo.service;

import com.haminime.photo.client.KakaoApiClient;
import com.haminime.photo.client.KakaoAuthClient;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.domain.entity.KakaoUser;
import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.repository.KakaoUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthKakaoService {

    private final KakaoAuthClient authClient;
    private final KakaoApiClient apiClient;
    private final KakaoUserRepository kakaoUserRepository;

    @Autowired
    private Environment env;

    @Value("${oauth.kakao.rest_api_key}")
    private String restapiKey;
    @Value("${domain}")
    private String domain;

    public void tryLogin(){
        authClient.tryLoginRequest();
    }

    public String getToken(String code) {
        String callback = "/oauth2/callback/login/kakao";
        Map<String, Object> tokenData = authClient.getToken(restapiKey, domain + callback, code, "authorization_code");
        if(tokenData.containsKey("access_token")){
            return tokenData.get("access_token").toString();
        }
        throw new CommonException();
    }

    public PlatformUser getInfo(String token){
        Map<String, Object> tokenData = apiClient.getInfo("Bearer " + token, "application/x-www-form-urlencoded;charset=utf-8");
        if(tokenData.containsKey("kakao_account")){
            Map<String, Object> kakaoAccount = (Map<String, Object>) tokenData.get("kakao_account");
            Map<String, Object> userInfo = (Map<String, Object>) kakaoAccount.get("profile");
            return new PlatformUser(tokenData.get("id").toString(), userInfo.get("nickname").toString());
        }
        log.error("Kakao account not found");
        throw new CommonException();
    }

    public long searchUser(long id) {
        return kakaoUserRepository.findById(id).map(KakaoUser::getUserId).orElse(-1L);
    }

    public void registUser(long id, long userId) {
        KakaoUser kakaoUser = KakaoUser.createInstance(id, userId);
        kakaoUserRepository.save(kakaoUser);
    }


}
