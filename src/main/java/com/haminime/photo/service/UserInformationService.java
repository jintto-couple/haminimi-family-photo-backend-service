package com.haminime.photo.service;

import com.haminime.photo.domain.entity.UserInformation;
import com.haminime.photo.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {

    private final UserInformationRepository userInformationRepository;
    private final String defaultUrl;

    UserInformationService(@Autowired UserInformationRepository userInformationRepository, @Value("${profile.default-url}") String defaultUrl) {
        this.userInformationRepository = userInformationRepository;
        this.defaultUrl = defaultUrl;
    }

    public void registUser(long userId, String userName) {
        UserInformation newUserInfo = UserInformation.createInstance(userId, userName, defaultUrl);
        userInformationRepository.save(newUserInfo);
    }
}
