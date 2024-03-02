package com.haminime.photo.controller.response;

import lombok.Getter;

@Getter
public class StartUploadResponse {

    //필요한가?
    private String fileContentId;
    private String remoteKey;
    private String uploadId;
}
