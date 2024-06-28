package com.haminime.photo.client;

import com.haminime.photo.controller.dto.response.KakaoTokenInfoResponse;
import com.haminime.photo.controller.dto.response.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoClient {

    @RequestMapping(method = RequestMethod.POST, value = "https://kauth.kakao.com/oauth/token")
    KakaoTokenResponse getToken(@RequestParam("client_id") String restApiKey,
                                @RequestParam("redirect_uri") String redirectUrl,
                                @RequestParam("code") String code,
                                @RequestParam("grant_type") String grantType);

    @RequestMapping(method = RequestMethod.POST, value = "https://kapi.kakao.com/v2/user/me")
    KakaoTokenInfoResponse getInfo(@RequestHeader("Authorization") String accessToken);
}
