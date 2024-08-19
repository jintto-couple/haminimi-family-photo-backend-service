package com.haminime.photo.repository;

import com.haminime.photo.domain.entity.User;
import com.haminime.photo.domain.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

}
