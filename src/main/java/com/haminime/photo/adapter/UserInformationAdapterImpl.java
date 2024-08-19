package com.haminime.photo.adapter;

import com.haminime.photo.domain.entity.UserInformation;
import com.haminime.photo.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserInformationAdapterImpl implements UserInformationAdapter {

    private final UserInformationService userInformationService;

    @Override
    public void registUser(long userId, String userName) {
        userInformationService.registUser(userId, userName);
    }

}
