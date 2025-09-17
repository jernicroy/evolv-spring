package com.evolv.care.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserInfo {
    private Long id;
    private String userName;
    private String shortName;
    private String email;
    private String hashCode;
    private String role;
    private String createdBy;
    private Instant createdDate;
}