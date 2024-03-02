package com.haminime.photo.service;

import com.haminime.photo.domain.FileContent;
import com.haminime.photo.service.dto.StartUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileContentService {

    private final MultipartUploadService multipartUploadService;

    public void getByPage(int page) {

    }

    public StartUpload startUpload(String name, String desc, String userId) {
        //Key생성 룰
        String key = new StringBuffer(userId)
                .append("-")
                .append(name)
                .append("-")
                .append(UUID.randomUUID().toString()).toString();

        CreateMultipartUploadResponse createResponse = multipartUploadService.createMultipartUpload(key);

        FileContent fileContent = FileContent.createInstance(key, createResponse.uploadId(), desc, userId);
        //fileContentRepository.save(fileContent);

        return StartUpload.builder()
                .key(fileContent.getRemoteKey())
                .uploadId(fileContent.getUploadId()).build();
    }

    public String requestPresignedUrl(String key, String uploadId, int partNumber) {
        return multipartUploadService.partUrlRequest(key, uploadId, partNumber).toString();
    }

    public void completeUpload(String key, String uploadId, Map<Integer, String> parts) {
        List<CompletedPart> completedParts = new ArrayList<>();
        for (Integer part : parts.keySet()) {
            String eTag = parts.get(key);
            completedParts.add(CompletedPart.builder().partNumber(part).eTag(eTag).build());
        }

        CompleteMultipartUploadResponse completeResponse = multipartUploadService.completeMultipartUpload(key, uploadId, completedParts);

        //FileContent fileContent = fileContentRepository.findByKeyAndUploadId(key,uploadId);
        //fileContent.complete(completeResponse.location());
        //fileContentRepository.save(fileContent);
    }


    public void deleteUpload(String key, String uploadId) {

        multipartUploadService.abortMultipartUpload(key, uploadId);

        //FileContent fileContent = fileContentRepository.findByKeyAndUploadId(key,uploadId);
        //fileContent.delete();
        //fileContentRepository.save(fileContent);
    }
}
