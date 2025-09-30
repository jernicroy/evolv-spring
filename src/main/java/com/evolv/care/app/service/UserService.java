package com.evolv.care.app.service;

import com.evolv.care.app.dto.UserInfo;
import com.evolv.care.app.entity.UserEntity;
import com.evolv.care.app.exception.EVOLV_ERROR;
import com.evolv.care.app.exception.ServerException;
import com.evolv.care.app.repo.UserRepository;
import com.evolv.care.app.util.SecurityUtils;
import com.evolv.care.app.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserInfo createUser(UserInfo userInfo) {
        Long userId = SecurityUtils.getAuthenticatedUserId();

        System.out.println("Authenticated User ID: "+ userId);
        userRepository.findByUserName(userInfo.getUserName())
                .ifPresent(u -> {
                    throw ServerException.error(EVOLV_ERROR.USER_ALREADY_EXISTS, userInfo.getUserName());
                });

        assert userId != null;
        UserEntity user = UserEntity.builder()
                .userName(userInfo.getUserName())
                .shortName(userInfo.getShortName())
                .email(userInfo.getEmail())
                .userHashCode(passwordEncoder.encode(userInfo.getUserHashCode()))
                .role(userInfo.getRole())
                .createdBy(String.valueOf(userId))
                .createdDate(Instant.now())
                .build();

        UserEntity saved = userRepository.save(user);

        log.info("{} User with id {} Created Successfully by {}", saved.getUserName(), saved.getId(), userId);
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

    public UserInfo getUserByName(String username){
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> ServerException.error(EVOLV_ERROR.USER_NOT_FOUND, username));

        return UserInfo.fromEntity(user);
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
