package com.haminime.photo.controller.dto.response;

import com.haminime.photo.enumeration.UploadStatus;
import jakarta.persistence.Id;

public class FileContentResponse {

    @Id
    private String id;
    private String remoteKey;
    private String remotePath;
    private String remoteThumbnailPath;
    private String uploadId;
    private String desc;
    private UploadStatus status;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
}
