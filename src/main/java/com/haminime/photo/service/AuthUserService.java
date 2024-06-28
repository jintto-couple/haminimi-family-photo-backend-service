package com.haminime.photo.service;

import com.haminime.photo.domain.entity.PlatformUser;
import com.haminime.photo.domain.entity.User;
import com.haminime.photo.domain.entity.UserInfo;
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
    private final AuthKakaoService authKakaoService;

    private final UserRepository userRepository;

    private final Map<Integer, AuthPlatformService> whiteAuth = new HashMap<>();
    {
        whiteAuth.put(1, authKakaoService);
    }

    @Transactional
    public User login(int userPlatform, String id){
        Optional<PlatformUser> platformUser = Optional.ofNullable(whiteAuth.get(userPlatform).tryLogin(id));
        if(platformUser.isPresent()){
            return getUser(platformUser.get().getUserNo);
        }
        User user = User.createInstance(userPlatform);
        userRepository.save(user);
        PlatformUser savedPlatformUser = whiteAuth.get(userPlatform).registUser(id, user.getUserNo);
        throw new needRegistException(user.getUserNo);
    }

    private User getUser(int userNo){
        return userRepository.findByUserNo(userNo);
    }



}
