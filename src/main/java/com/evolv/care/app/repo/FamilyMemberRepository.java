package com.evolv.care.app.repo;

import com.evolv.care.app.entity.FamilyMemberEntity;
import com.evolv.care.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity, Long> {
    List<FamilyMemberEntity> findByUser(UserEntity user);
}