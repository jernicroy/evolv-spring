package com.evolv.care.app.repo;

import com.evolv.care.app.entity.AdherenceLogEntity;
import com.evolv.care.app.entity.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdherenceLogRepository extends JpaRepository<AdherenceLogEntity, Long> {
    List<AdherenceLogEntity> findByMedication(MedicationEntity medication);
}