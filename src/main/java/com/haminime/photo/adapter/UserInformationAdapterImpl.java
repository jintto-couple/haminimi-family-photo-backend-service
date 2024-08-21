package com.haminime.photo.adapter;

import com.haminime.photo.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserInformationAdapterImpl implements UserInformationAdapter {

    private final UserInformationService userInformationService;

    @Override
    public void createUser(long userId, String userName) {
        userInformationService.createUser(userId, userName);
    }

}
