package com.haminime.photo.controller;

import com.haminime.photo.common.CommonException;
import com.haminime.photo.controller.dto.response.UserInformationResponse;
import com.haminime.photo.service.UserInformationService;
import com.haminime.photo.service.dto.UserAuthentication;
import com.haminime.photo.service.dto.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserInformationService userInformationService;

    @GetMapping("")
    public UserInformationResponse getUser() {
        UserAuthentication auth = UserAuthenticationHolder.getUserAuthentication();
        if(auth != null) {
            return userInformationService.searchUser(auth.getId());
        }
        log.error("Required User Authentication When Get User");
        throw new CommonException();
    }

}
