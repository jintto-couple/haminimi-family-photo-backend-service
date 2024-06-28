package com.haminime.photo.service;

import com.haminime.photo.domain.entity.PlatformUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthPlatformService {
    public Optional<PlatformUser> tryLogin(String id);
    public PlatformUser registUser(String id, int userNo);
}
