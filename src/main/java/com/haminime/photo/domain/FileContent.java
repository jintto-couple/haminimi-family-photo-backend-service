package com.haminime.photo.domain;


import com.haminime.photo.enumeration.UploadStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileContent {

    @Id
    private String id;
    private String originName;
    private String uploadId;
    private String path;
    private String name;
    private String desc;
    private UploadStatus status;
    private String createdAt;
    private String createdBy;
    private String updatedAt;

    public static FileContent createInstance(String key, String uploadId, String name, String desc, String createdBy) {
        return FileContent.builder().id(UUID.randomUUID().toString())
                .originName(key)
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
        setPath(url);
        setUpdatedAt(LocalDateTime.now().toString());
    }

    public void delete() {
        setStatus(UploadStatus.DELETED);
        setUpdatedAt(LocalDateTime.now().toString());
    }
}
