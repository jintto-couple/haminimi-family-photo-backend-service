package com.haminime.photo.controller.dto.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class CompleteUploadRequest {

    private String remoteKey;
    private String uploadId;
    private Map<Integer, String> parts;
}
