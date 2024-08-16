package com.haminime.photo.controller;

import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.controller.dto.response.KakaoTokenInfoResponse;
import com.haminime.photo.domain.entity.User;
import com.haminime.photo.service.AuthKakaoService;
import com.haminime.photo.service.AuthUserService;
import com.haminime.photo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController("/oauth2/login")
public class AuthLoginController {
    private final AuthKakaoService authKakaoService;
    private final AuthUserService authUserService;
    private final JwtUtil jwtUtil;

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoAuth(String code) {
        return null;
    }

    @PostMapping("/reissue")
    public AccessTokenResponse reissue(String refreshToken) {
        return null;
    }
}
