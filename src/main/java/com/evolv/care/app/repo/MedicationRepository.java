package com.evolv.care.app.repo;

import com.evolv.care.app.entity.FamilyMemberEntity;
import com.evolv.care.app.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {
    List<MedicationEntity> findByFamilyMember(FamilyMemberEntity familyMember);
}