package com.haminime.photo.domain.entity;


import jakarta.persistence.*;
import lombok.*;



@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private int userPlatform;

    public static User createInstance(int userPlatform) {
        return User.builder().userPlatform(userPlatform).build();
    }
}
