package com.haminime.photo.controller;

import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.service.AuthKakaoService;
import com.haminime.photo.service.AuthUserService;
import com.haminime.photo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/oauth2/login")
public class AuthLoginController {
    private final AuthKakaoService authKakaoService;
    private final AuthUserService authUserService;
    private final JwtUtil jwtUtil;

    @PostMapping("/kakao")
    public void kakaoLogin() {
        authUserService.loginRequest(AuthPlatform.kakao);
    }

    @PostMapping("/reissue")
    public AccessTokenResponse reissue(String refreshToken) {
        return null;
    }
}
