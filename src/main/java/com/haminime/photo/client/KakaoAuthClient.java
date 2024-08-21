package com.haminime.photo.client;

import com.haminime.photo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "kakaoAuthClient", url = "${oauth.kakao.url.kauth}", configuration = FeignConfiguration.class)
public interface KakaoAuthClient {

    @RequestMapping(method = RequestMethod.POST, value = "/token")
    Map<String, Object> getToken(@RequestHeader("Content-type") String contentType,
                                 @RequestParam("client_id") String restApiKey,
                                 @RequestParam("redirect_uri") String redirectUrl,
                                 @RequestParam("code") String code,
                                 @RequestParam("grant_type") String grantType);

}
