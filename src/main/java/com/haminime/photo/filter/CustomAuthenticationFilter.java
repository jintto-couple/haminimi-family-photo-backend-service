package com.haminime.photo.filter;

import com.haminime.photo.common.CommonException;
import com.haminime.photo.domain.entity.User;
import com.haminime.photo.domain.entity.UserInformation;
import com.haminime.photo.service.dto.UserAuthentication;
import com.haminime.photo.service.dto.UserAuthenticationHolder;
import com.haminime.photo.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        if(path.startsWith("/oauth2/login") || path.startsWith("/oauth2/callback")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        setAuthentication(request);
        filterChain.doFilter(servletRequest, servletResponse);
        UserAuthenticationHolder.removeUserAuthentication();
    }

    private void setAuthentication(HttpServletRequest req) {
        String token = fetchAccessTokenByRequest(req);
        UserAuthentication userAuth = validateToken(token);
        UserAuthenticationHolder.setUserAuthentication(userAuth);
    }

    private String fetchAccessTokenByRequest(HttpServletRequest req) {
        String AccessToken = req.getHeader("Authorization");
        if(StringUtils.hasText(AccessToken) && AccessToken.startsWith("Bearer ") ){
            return AccessToken.substring(7);
        } else {
            log.error("Authorization header is incorrect");
            throw new CommonException();
        }
    }

    private UserAuthentication validateToken(String token) {
        return jwtUtil.validAndGetUser(token);
    }
}
