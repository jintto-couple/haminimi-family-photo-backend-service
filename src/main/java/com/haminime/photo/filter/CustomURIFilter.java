package com.haminime.photo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class CustomURIFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        log.info("request path = {}", path);
        if (path.startsWith("/api/oauth2")) {
            String newPath = path.substring(4);

            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
                @Override
                public String getRequestURI() {
                    return newPath;
                }

                @Override
                public String getServletPath() {
                    return newPath;
                }
            };

            chain.doFilter(requestWrapper, response);
            return;
        }
        chain.doFilter(request, response);
    }
}