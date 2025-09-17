package com.evolv.care.app.repo;

import com.evolv.care.app.entity.AppointmentEntity;
import com.evolv.care.app.entity.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByFamilyMember(FamilyMemberEntity familyMember);
}