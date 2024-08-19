package com.haminime.photo.controller;

import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2/login")
public class AuthLoginController {

    private final AuthUserService authUserService;

    @PostMapping("/kakao")
    public void kakaoLogin() {
        authUserService.loginRequest(AuthPlatform.kakao);
    }

    @GetMapping("/reissue")
    public AccessTokenResponse reissueAccessToken(String refreshToken) {
        return authUserService.reissueTokenByRefreshToken(refreshToken);
    }
}
