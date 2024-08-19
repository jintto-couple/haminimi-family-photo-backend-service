package com.haminime.photo.enumeration;

import com.haminime.photo.adapter.AuthKakaoAdapter;
import com.haminime.photo.adapter.AuthPlatformAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthPlatform {
    kakao(1, AuthKakaoAdapter.class);

    private final int registration;
    private final Class<? extends AuthPlatformAdapter> adapterClass;

}
