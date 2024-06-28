package com.haminime.photo.controller.dto.response;

import lombok.Getter;

@Getter
public class KakaoTokenInfoResponse {
    private String id;
    private int expiresIn;
    private int appId;
}
