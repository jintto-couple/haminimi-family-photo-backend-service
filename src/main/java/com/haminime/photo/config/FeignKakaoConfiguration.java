package com.haminime.photo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
public class FeignKakaoConfiguration {

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}

