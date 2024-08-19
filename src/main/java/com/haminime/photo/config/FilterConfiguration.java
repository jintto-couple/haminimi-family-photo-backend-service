package com.haminime.photo.config;

import com.haminime.photo.filter.CustomAuthenticationFilter;
import com.haminime.photo.filter.CustomURIFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfiguration {

    private final CustomURIFilter customURIFilter;
    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public FilterRegistrationBean<CustomURIFilter> customURIFilterBean() {
        FilterRegistrationBean<CustomURIFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customURIFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> customAuthenticationFilterBean() {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customAuthenticationFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
