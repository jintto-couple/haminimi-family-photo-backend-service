package com.haminime.photo.repository;

import com.haminime.photo.domain.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContent, String> {
    FileContent findByRemoteKeyAndUploadId(String remoteKey, String uploadId);
}
