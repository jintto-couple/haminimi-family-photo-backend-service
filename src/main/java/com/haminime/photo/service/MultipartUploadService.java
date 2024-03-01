package com.haminime.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultipartUploadService {

    private final S3Client s3Client;

    private static String BUCKET_NAME = "haminimi-photo";

    private static String REGION = "";

    /* AWS MultipartUpload 참고
    https://techblog.woowahan.com/11392/
    https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/mpuoverview.html
    https://docs.aws.amazon.com/ko_kr/sdk-for-java/latest/developer-guide/examples-s3-objects.html#list-objects
    */
    public CreateMultipartUploadResponse createMultipartUpload(String key) {

        return s3Client.createMultipartUpload(b -> b
                .bucket(BUCKET_NAME)
                .key(key));
    }

    public URL partUrlRequest(String key, String uploadId, int partNumber) {
        S3Presigner presigner = S3Presigner.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.of(REGION))
                .build();

        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .uploadId(uploadId)
                .partNumber(partNumber)
                .build();

        PresignedUploadPartRequest presignedRequest = presigner.presignUploadPart(UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .uploadPartRequest(uploadPartRequest)
                .build());

        URL presignedUrl = presignedRequest.url();

        presigner.close();

        return presignedUrl;
    }

    public CompleteMultipartUploadResponse completeMultipartUpload(String key, String uploadId, List<CompletedPart> completedParts) {

        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(completedParts)
                .build();

        CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .uploadId(uploadId)
                .multipartUpload(completedMultipartUpload)
                .build();

        return s3Client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    public AbortMultipartUploadResponse abortMultipartUpload(String key, String uploadId) {

        AbortMultipartUploadRequest abortRequest = AbortMultipartUploadRequest.builder()
                                                    .bucket(BUCKET_NAME)
                                                    .key(key)
                                                    .uploadId(uploadId)
                                                    .build();

        return s3Client.abortMultipartUpload(abortRequest);
    }
}
