package com.haminime.photo.service;

import com.haminime.photo.domain.FileContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.net.URL;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FileContentService {

    static final S3Client s3Client = S3Client.create();
    private static String BUCKET_NAME = "haminimi-photo";

    private static String REGION = "";

    //https://docs.aws.amazon.com/ko_kr/sdk-for-java/latest/developer-guide/examples-s3-objects.html#list-objects
    //https://techblog.woowahan.com/11392/
    //https://techblog.woowahan.com/11392/

    public String initialMultipartUpload(String name, String desc, String userId) {

        //TODO :: Upload할 파일 이름 만들기
        String key = "";

        CreateMultipartUploadResponse createMultipartUploadResponse = s3Client.createMultipartUpload(b -> b
                .bucket(BUCKET_NAME)
                .key(key));

        FileContent fileContent = FileContent.createInstance(key, name, desc, userId);

        //TODO :: FileContents repository에 create
        //fileContentRepository.save(fileContent);

        //uploadId, key 반환
        return createMultipartUploadResponse.uploadId();
    }

    //업로드할 part의 URL발급 Request
    public URL partUrlRequest(String uploadId, String key, int partNumber) {
        S3Presigner presigner = S3Presigner.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.of(REGION)) // 버킷이 위치한 리전을 설정합니다.
                .build();

        // 멀티파트 업로드의 파트를 업로드하기 위한 요청 객체를 생성
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .uploadId(uploadId)
                .partNumber(partNumber)
                .build();

        // Presigned 요청을 생성합니다. 유효 시간은 예를 들어 60분으로 설정할 수 있습니다.
        PresignedUploadPartRequest presignedRequest = presigner.presignUploadPart(UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(60))
                .uploadPartRequest(uploadPartRequest)
                .build());

        // Presigned URL을 반환
        URL presignedUrl = presignedRequest.url();

        // Presigner를 종료
        presigner.close();

        return presignedUrl;
    }

    public void completeMultipartUpload() {

    }

    public void deleteMultipartUpload() {

    }

    public void getByPage(int page) {

    }
}
