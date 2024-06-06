package com.haminime.photo.controller.dto.response;

import lombok.Getter;

@Getter
public class StartUploadResponse {

    //필요한가?
    private String fileContentId;
    private String remoteKey;
    private String uploadId;
}
