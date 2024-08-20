package com.haminime.photo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableFeignClients
@SpringBootApplication
@Slf4j
public class HaminimePhotoBackendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaminimePhotoBackendServiceApplication.class, args);
    }

}
