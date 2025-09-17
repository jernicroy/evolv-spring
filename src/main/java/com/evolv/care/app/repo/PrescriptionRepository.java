package com.evolv.care.app.repo;

import com.evolv.care.app.entity.FamilyMemberEntity;
import com.evolv.care.app.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {
    List<PrescriptionEntity> findByFamilyMember(FamilyMemberEntity familyMember);
}
