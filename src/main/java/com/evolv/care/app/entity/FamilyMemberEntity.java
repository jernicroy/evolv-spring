package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t200_family_member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c200_family_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c200_user_id", nullable = false)
    private UserEntity user;

    @Column(name = "c200_name")
    private String name;

    @Column(name = "c200_date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "c200_relationship")
    private String relationship;

    @Column(name = "c200_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c200_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c200_updated_date")
    private Instant updatedDate;

    @Column(name = "c200_updated_by")
    private String updatedBy;

    @Column(name = "c200_void_fl")
    private String voidFlag;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationEntity> medications;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointments;

    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionEntity> prescriptions;
}

