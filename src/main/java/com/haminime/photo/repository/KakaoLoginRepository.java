package com.haminime.photo.repository;

import com.haminime.photo.domain.entity.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoLoginRepository extends JpaRepository<KakaoUser, String> {

}
