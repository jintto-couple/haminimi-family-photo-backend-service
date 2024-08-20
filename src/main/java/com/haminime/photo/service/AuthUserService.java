package com.haminime.photo.service;

import com.haminime.photo.adapter.AdapterProvider;
import com.haminime.photo.adapter.UserInformationAdapter;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.controller.dto.response.LoginTokenResponse;
import com.haminime.photo.service.dto.PlatformUser;
import com.haminime.photo.domain.entity.User;
import com.haminime.photo.enumeration.AuthPlatform;
import com.haminime.photo.repository.UserRepository;
import com.haminime.photo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthUserService {
    private final AdapterProvider provider;
    private final UserInformationAdapter userInformationAdapter;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public void loginRequest(AuthPlatform platform){
        provider.getAdapter(platform)
                .loginRequest();
    }

    public LoginTokenResponse getLoginToken(AuthPlatform platform, String code){
        User user = getLoginUser(platform, code);
        return makeToken(user);
    }

    private User getLoginUser(AuthPlatform platform, String code){
        String token = getToken(platform, code);
        PlatformUser user = getInfo(platform, token);
        return searchUser(platform, user);
    }

    private String getToken(AuthPlatform platform, String code){
        return  provider.getAdapter(platform)
                .getAccessToken(code);
    }

    private PlatformUser getInfo(AuthPlatform platform, String token){
        return provider.getAdapter(platform)
                .getInfo(token);
    }

    private User searchUser(AuthPlatform platform, PlatformUser user){
        long userId = provider.getAdapter(platform)
                .searchUserIdById(user.getId());
        if(userId == -1){
            return registUser(platform, user);
        } else {
            return userRepository.findById(userId)
                    .orElseThrow(CommonException::new);
        }
    }

    private User registUser(AuthPlatform platform, PlatformUser user) {
        User newUser = User.createInstance(platform.getRegistration());
        userInformationAdapter.registUser(newUser.getUserId(), user.getUserName());
        provider.getAdapter(platform)
                .registUser(user.getId(), newUser.getUserId());
        return newUser;
    }

    private LoginTokenResponse makeToken(User user) {
        String accessToken = jwtUtil.createToken(user.getUserId());
//        String refreshToken =
        return new LoginTokenResponse(accessToken, "RefreshToken");
    }

    public AccessTokenResponse reissueTokenByRefreshToken(String refreshToken){
        return new AccessTokenResponse("Reissued AccessToken");
    }

}