package com.haminime.photo.controller;

import com.haminime.photo.controller.dto.request.CompleteUploadRequest;
import com.haminime.photo.controller.dto.request.PresignedUrlRequest;
import com.haminime.photo.controller.dto.request.StartUploadRequest;
import com.haminime.photo.controller.dto.response.CompleteUploadResponse;
import com.haminime.photo.controller.dto.response.FileContentResponse;
import com.haminime.photo.controller.dto.response.PresignedUrlResponse;
import com.haminime.photo.controller.dto.response.StartUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController("fileContent")
public class FileContentController {

    @GetMapping("{id}")
    public FileContentResponse getById(@PathVariable String id) {

        return null;
    }

    @PostMapping
    public Page<FileContentResponse> getAllByPage(@RequestBody Pageable pageable) {

        return null;
    }

    @PostMapping("upload")
    public StartUploadResponse startUpload(@RequestBody StartUploadRequest request) {

        return null;
    }

    @PutMapping("upload")
    public CompleteUploadResponse completeUpload(@RequestBody CompleteUploadRequest request) {

        return null;
    }

    @PostMapping("url")
    public PresignedUrlResponse requestPresignedUrl(@RequestBody PresignedUrlRequest request) {

        return null;
    }
}
