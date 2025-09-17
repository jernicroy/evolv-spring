package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "t500_adherence_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdherenceLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c500_adherence_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c500_medication_id", nullable = false)
    private MedicationEntity medication;

    @Column(name = "c500_taken", nullable = false)
    private boolean taken;

    @Column(name = "c500_timestamp", nullable = false)
    private Instant timestamp = Instant.now();

    @Column(name = "c500_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c500_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c500_updated_date")
    private Instant updatedDate;

    @Column(name = "c500_updated_by")
    private String updatedBy;

    @Column(name = "c500_void_fl")
    private String voidFlag;
}
