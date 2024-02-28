package com.haminime.photo.domain;


import com.haminime.photo.enumeration.UploadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FileContent {
    private String id;
    private String key;
    private String uploadId;
    private String url;
    private String name;
    private String desc;
    private UploadStatus status;
    private String createdAt;
    private String createdBy;
    private String updatedAt;

    public static FileContent createInstance(String key, String uploadId, String name, String desc, String createdBy) {
        return FileContent.builder().id(UUID.randomUUID().toString())
                .key(key)
                .uploadId(uploadId)
                .name(name)
                .desc(desc)
                .status(UploadStatus.INIT)
                .createdBy(createdBy)
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .build();
    }

    public void complete(String url) {
        setStatus(UploadStatus.UPLOADED);
        setUrl(url);
        setUpdatedAt(LocalDateTime.now().toString());
    }

    public void delete() {
        setStatus(UploadStatus.DELETED);
        setUpdatedAt(LocalDateTime.now().toString());
    }
}
