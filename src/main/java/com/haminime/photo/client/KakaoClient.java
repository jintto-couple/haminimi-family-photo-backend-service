package com.haminime.photo.client;

import com.haminime.photo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "kakaoClient", configuration = FeignConfiguration.class)
public interface KakaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "https://kauth.kakao.com/oauth/authorize")
    void tryLoginRequest();

    @RequestMapping(method = RequestMethod.POST, value = "https://kauth.kakao.com/oauth/token")
    Map<String, Object> getToken(@RequestParam("client_id") String restApiKey,
                                 @RequestParam("redirect_uri") String redirectUrl,
                                 @RequestParam("code") String code,
                                 @RequestParam("grant_type") String grantType);

    @RequestMapping(method = RequestMethod.POST, value = "https://kapi.kakao.com/v2/user/me")
    Map<String, Object> getInfo(@RequestHeader("Authorization") String accessToken,
                                @RequestHeader("Content-type") String contentType);
}
