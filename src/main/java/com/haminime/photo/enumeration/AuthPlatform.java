package com.haminime.photo.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthPlatform {
    kakao(1);

    private final int registration;

}
