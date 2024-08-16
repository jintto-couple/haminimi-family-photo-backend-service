package com.haminime.photo.service;

import com.haminime.photo.adapter.AuthKakaoAdapter;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.domain.entity.User;
import com.haminime.photo.domain.entity.UserInfo;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthUserService {
    private final AuthKakaoAdapter authKakaoAdapter;

    private final UserRepository userRepository;

    public void loginRequest(AuthPlatform platform){
        if(platform.equals(AuthPlatform.kakao)){
            authKakaoService.login();
        } else {
            return;
//            throw new CommonException("Not Supported Platform");
        }
    }

    private User getUser(int userNo){
        return userRepository.findByUserNo(userNo);
    }



}
