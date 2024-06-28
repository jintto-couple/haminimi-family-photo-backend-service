package com.haminime.photo.controller;

import com.haminime.photo.common.CommonException;
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
@RestController("auth/kakao")
public class KakaoLoginController {
    private final AuthKakaoService authKakaoService;
    private final AuthUserService authUserService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> auth(String code) {
        KakaoTokenInfoResponse kakaoTokenInfoResponse = authKakaoService.getInfo(code);
        if(kakaoTokenInfoResponse != null) {
            User loginUser = authUserService.login(1, kakaoTokenInfoResponse.getId());
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("userNo", loginUser.getUserNo);
            String token = jwtUtil.createToken(tokenData);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        throw new CommonException();
    }
}
