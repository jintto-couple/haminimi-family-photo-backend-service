package com.haminime.photo.controller;

import com.haminime.photo.service.AuthUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AuthLoginControllerTest {

    @InjectMocks
    private AuthLoginController authLoginController;

    @Mock
    private AuthUserService authUserService;

    @Test
    public void testLogin() {
        
    }

}
