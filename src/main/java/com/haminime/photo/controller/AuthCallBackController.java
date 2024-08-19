package com.haminime.photo.controller;

import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.LoginTokenResponse;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2/callback")
@Slf4j
public class AuthCallBackController {

    private final AuthUserService authUserService;

    @GetMapping("/login/kakao")
    public LoginTokenResponse kakaoCallback(@RequestParam(value = "code", required = false) String code,
                                            @RequestParam(value = "error", required = false) String error,
                                            @RequestParam(value = "error_description", required = false) String errorDescription) {
        if(StringUtils.hasText(code)) {
            return authUserService.getLoginToken(AuthPlatform.kakao, code);
        } else {
            log.error(error);
            throw new CommonException();
        }
    }
}
