package com.haminime.photo.filter;

import com.haminime.photo.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
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
        //TO-DO 유저 객체 만들어서 넘기기
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
