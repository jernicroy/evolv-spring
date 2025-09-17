package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t300_medication")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c300_medication_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c300_family_member_id", nullable = false)
    private FamilyMemberEntity familyMember;

    @Column(name = "c300_name")
    private String name;

    @Column(name = "c300_dosage")
    private String dosage;

    @Column(name = "c300_frequency")
    private String frequency;

    @Column(name = "c300_start_date")
    private LocalDate startDate;

    @Column(name = "c300_end_date")
    private LocalDate endDate;

    @Column(name = "c300_notes")
    private String notes;

    @Column(name = "c300_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c300_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c300_updated_date")
    private Instant updatedDate;

    @Column(name = "c300_updated_by")
    private String updatedBy;

    @Column(name = "c300_void_fl")
    private String voidFlag;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdherenceLogEntity> adherenceLogs;
}
