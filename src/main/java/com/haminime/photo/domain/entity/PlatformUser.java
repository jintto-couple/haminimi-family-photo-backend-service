package com.haminime.photo.domain.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformUser {
    private int userNo;
}
