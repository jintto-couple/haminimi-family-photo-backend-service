package com.haminime.photo.client;

import com.haminime.photo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "kakaoApiClient", url = "${oauth.kakao.url.kapi}", configuration = FeignConfiguration.class)
public interface KakaoApiClient {

    @RequestMapping(method = RequestMethod.POST, value = "/v2/user/me")
    Map<String, Object> getInfo(@RequestHeader("Authorization") String accessToken,
                                @RequestHeader("Content-type") String contentType);
}
