package com.haminime.photo.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StartUpload {

    private String uploadId;

    private String key;
}
