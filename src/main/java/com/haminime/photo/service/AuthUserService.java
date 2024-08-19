package com.haminime.photo.service;

import com.haminime.photo.adapter.AuthKakaoAdapter;
import com.haminime.photo.adapter.UserInformationAdapter;
import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.AccessTokenResponse;
import com.haminime.photo.controller.dto.response.LoginTokenResponse;
import com.haminime.photo.domain.entity.PlatformUser;
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
    private final AuthKakaoAdapter authKakaoAdapter;
    private final UserInformationAdapter userInformationAdapter;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private <T> PlatformAction<T> fetchPlatformAction(AuthPlatform platform, PlatformAction<T> kakao) {
        return switch (platform) {
            case kakao -> kakao;
            default -> {
                log.error("Platform not supported: {}", platform);
                throw new CommonException();
            }
        };
    }

    public void loginRequest(AuthPlatform platform){
        PlatformAction<Void> action = fetchPlatformAction(platform,
                authKakaoAdapter::loginRequest);
        action.run();
    }

    public LoginTokenResponse getLoginToken(AuthPlatform platform, String code){
        User user = getLoginUser(platform, code);
        return makeToken(user);
    }

    private User getLoginUser(AuthPlatform platform, String code){
        String token = getToken(platform, code);
        PlatformUser user = getInfo(platform, token);
        return getUser(platform, user);
    }

    private String getToken(AuthPlatform platform, String code){
        PlatformAction<String> action = fetchPlatformAction(platform,
                () -> authKakaoAdapter.getAccessToken(code));
        return action.run();
    }

    private PlatformUser getInfo(AuthPlatform platform, String token){
        PlatformAction<PlatformUser> action = fetchPlatformAction(platform,
                () -> authKakaoAdapter.getInfo(token));
        return action.run();
    }

    private User getUser(AuthPlatform platform, PlatformUser user){
        PlatformAction<Long> action = fetchPlatformAction(platform,
                () -> authKakaoAdapter.searchUserIdById(user.getId()));
        long userId = action.run();
        if(userId == -1){
            return registUser(platform, user);
        } else {
            return userRepository.findById(userId).orElseThrow(CommonException::new);
        }
    }

    private User registUser(AuthPlatform platform, PlatformUser user) {
        User newUser = User.createInstance(platform.getRegistration());
        userInformationAdapter.registUser(newUser.getUserId(), user.getUserName());
        PlatformAction<Void> action = fetchPlatformAction(platform,
                () -> authKakaoAdapter.registUser(user.getId(), newUser.getUserId()));
        action.run();
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

@FunctionalInterface
interface PlatformAction<T> {
    T run();
}
