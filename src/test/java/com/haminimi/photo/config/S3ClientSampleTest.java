package com.haminimi.photo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class S3ClientSampleTest {
    @Autowired
    S3ClientSample s3ClientSample;

    @Test
    public void S3ClientSample() throws IOException {
        s3ClientSample.readFile();
    }

}