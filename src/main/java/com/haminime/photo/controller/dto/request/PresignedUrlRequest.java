package com.haminime.photo.controller.dto.request;

import lombok.Getter;

@Getter
public class PresignedUrlRequest {
    private String remoteKey;
    private String uploadId;
    private int partNumber;
}
