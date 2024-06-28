package com.haminime.photo.controller.dto.response;

import lombok.Getter;

@Getter

public class KakaoTokenResponse {
    private String tokenType;
    private String accessToken;
    private Long expiresIn;
}
