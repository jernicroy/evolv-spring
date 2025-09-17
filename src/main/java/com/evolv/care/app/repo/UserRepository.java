package com.evolv.care.app.repo;

import com.evolv.care.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    @Query(value = "UPDATE t100_user SET c100_void_fl = 'Y', c100_updated_date = now(), c100_updated_by = :updatedBy WHERE c100_user_id = :id", nativeQuery = true)
    int voidUserById(Long id, String updatedBy);
}
