package com.haminime.photo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2/callback")
public class AuthCallBackController {

    @PostMapping("/kakao")
    public ResponseEntity kakaoCallback(@RequestParam("Token") String Token) {

        return null;
    }
}
