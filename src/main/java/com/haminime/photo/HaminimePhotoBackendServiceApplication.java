package com.haminime.photo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class HaminimePhotoBackendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaminimePhotoBackendServiceApplication.class, args);
    }

}
