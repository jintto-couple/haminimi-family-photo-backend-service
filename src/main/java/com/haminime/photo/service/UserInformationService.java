package com.haminime.photo.service;

import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.UserInformationResponse;
import com.haminime.photo.domain.entity.UserInformation;
import com.haminime.photo.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInformationService {

    private final UserInformationRepository userInformationRepository;

    @Value("${profile.default-url}")
    private String defaultUrl;

    public void registUser(long userId, String userName) {
        UserInformation newUserInfo = UserInformation.createInstance(userId, userName, defaultUrl);
        userInformationRepository.save(newUserInfo);
    }

    public UserInformationResponse searchUser(long userId) {
        UserInformation user = userInformationRepository.findById(userId).orElseThrow(CommonException::new);
        return new UserInformationResponse(user.getUserName(), user.getProfileUrl());
    }
}
