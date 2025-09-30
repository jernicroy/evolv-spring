package com.evolv.care.app.dto;

import com.evolv.care.app.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String userName;
    private String shortName;
    private String email;
    private String userHashCode;
    private String role;
    private String token;
    private String createdBy;
    private Instant createdDate;

    public static UserInfo fromEntity(UserEntity entity) {
        return UserInfo.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .shortName(entity.getShortName())
                .email(entity.getEmail())
                .userHashCode(entity.getUserHashCode())
                .role(entity.getRole())
                .token(entity.getToken())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}