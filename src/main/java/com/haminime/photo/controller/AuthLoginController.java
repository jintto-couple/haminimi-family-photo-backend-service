package com.haminime.photo.controller;

import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.controller.dto.response.LoginTokenResponse;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2/login")
public class AuthLoginController {

    private final AuthUserService authUserService;

    @GetMapping("/kakao")
    public ResponseEntity<Void> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(authUserService.loginRequest(AuthPlatform.kakao)));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/reissue")
    public LoginTokenResponse reissueAccessToken(String refreshToken) {
        return authUserService.reissueTokenByRefreshToken(refreshToken);
    }
}
