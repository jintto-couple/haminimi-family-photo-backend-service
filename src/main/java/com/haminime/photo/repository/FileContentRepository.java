package com.haminime.photo.repository;

import com.haminime.photo.domain.entity.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent, String> {
    FileContent findByRemoteKeyAndUploadId(String remoteKey, String uploadId);
}
