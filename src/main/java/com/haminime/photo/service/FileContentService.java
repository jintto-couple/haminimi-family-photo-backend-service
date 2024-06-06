package com.haminime.photo.service;

import com.haminime.photo.domain.entity.FileContent;
import com.haminime.photo.repository.FileContentRepository;
import com.haminime.photo.service.dto.CompleteUpload;
import com.haminime.photo.service.dto.RequestPresignedUrl;
import com.haminime.photo.service.dto.StartUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FileContentService {

    private final MultipartUploadService multipartUploadService;

    private final FileContentRepository fileContentRepository;

    public Page<FileContent> getAllByPage(Pageable pageable) {
        return fileContentRepository.findAll(pageable);
    }

    public FileContent getById(String id) {
        return fileContentRepository.findById(id).orElseGet(() -> null);
    }

    public StartUpload startUpload(String description, String userId) {
        //Key생성 룰
        String key = new StringBuffer(userId)
                .append("-")
                .append(UUID.randomUUID().toString()).toString();

        CreateMultipartUploadResponse createResponse = multipartUploadService.createMultipartUpload(key);
        FileContent fileContent = FileContent.createInstance(key, createResponse.uploadId(), description, userId);

        //fileContentRepository.save(fileContent);

        return StartUpload.builder()
                .key(fileContent.getRemoteKey())
                .uploadId(fileContent.getUploadId()).build();
    }

    public RequestPresignedUrl requestPresignedUrl(String key, String uploadId, int partNumber) {
        String partUrl = multipartUploadService.partUrlRequest(key, uploadId, partNumber).toString();

        return RequestPresignedUrl.builder()
                .url(partUrl).build();
    }

    public CompleteUpload completeUpload(String key, String uploadId, Map<Integer, String> parts) {
        List<CompletedPart> completedParts = new ArrayList<>();
        for (Integer part : parts.keySet()) {
            String eTag = parts.get(key);
            completedParts.add(CompletedPart.builder().partNumber(part).eTag(eTag).build());
        }

        CompleteMultipartUploadResponse completeResponse = multipartUploadService.completeMultipartUpload(key, uploadId, completedParts);

        FileContent fileContent = fileContentRepository.findByRemoteKeyAndUploadId(key, uploadId);
        fileContent.complete(completeResponse.location());
        FileContent persistFileContent = fileContentRepository.save(fileContent);

        return CompleteUpload.builder()
                .fileContentId(persistFileContent.getId())
                .uploadId(persistFileContent.getUploadId()).build();
    }

    public void delete(String id) {
        Optional<FileContent> fileContent = fileContentRepository.findById(id);
        if (fileContent.isPresent()) {
            fileContent.get().delete();
            fileContentRepository.save(fileContent.get());
        }
    }

    public void cancelUpload(String key, String uploadId) {

        FileContent fileContent = fileContentRepository.findByRemoteKeyAndUploadId(key, uploadId);
        fileContent.delete();
        fileContentRepository.save(fileContent);

        multipartUploadService.abortMultipartUpload(key, uploadId);
    }
}
