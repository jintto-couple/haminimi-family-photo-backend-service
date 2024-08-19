package com.haminime.photo.config;

import com.haminime.photo.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfiguration {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> customAuthenticationFilterBean() {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customAuthenticationFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setName("customAuthenticationFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
