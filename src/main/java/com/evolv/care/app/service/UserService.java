package com.evolv.care.app.service;

import com.evolv.care.app.dto.UserInfo;
import com.evolv.care.app.entity.UserEntity;
import com.evolv.care.app.repo.UserRepository;
import com.evolv.care.app.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfo createUser(UserInfo userInfo) {
        Long userId = SecurityUtils.getAuthenticatedUserId();

        userRepository.findByUserName(userInfo.getUserName())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("User already exists: " + userInfo.getUserName());
                });

        UserEntity user = UserEntity.builder()
                .userName(userInfo.getUserName())
                .shortName(userInfo.getShortName())
                .email(userInfo.getEmail())
                .hashCode(userInfo.getHashCode())
                .role(userInfo.getRole())
                .createdBy(userId != null ? userId.toString() : "1")
                .createdDate(Instant.now())
                .build();

        UserEntity saved = userRepository.save(user);

        return UserInfo.builder()
                .id(saved.getId())
                .userName(saved.getUserName())
                .shortName(saved.getShortName())
                .email(saved.getEmail())
                .role(saved.getRole())
                .createdBy(saved.getCreatedBy())
                .createdDate(saved.getCreatedDate())
                .build();
    }

    public List<UserInfo> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserInfo.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .shortName(user.getShortName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .createdBy(user.getCreatedBy())
                        .createdDate(user.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    public UserInfo getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> UserInfo.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .shortName(user.getShortName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .createdBy(user.getCreatedBy())
                        .createdDate(user.getCreatedDate())
                        .build())
                .orElse(null);
    }

    @Modifying
    @Transactional
    public int voidUserById(Long id) {
        Long userId = SecurityUtils.getAuthenticatedUserId();

        return userRepository.voidUserById(id, String.valueOf(userId));
    }
}
