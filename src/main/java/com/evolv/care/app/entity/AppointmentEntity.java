package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "t400_appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c400_appointment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c400_family_member_id", nullable = false)
    private FamilyMemberEntity familyMember;

    @Column(name = "c400_type")
    private String type;

    @Column(name = "c400_date_time")
    private Instant dateTime;

    @Column(name = "c400_notes")
    private String notes;

    @Column(name = "c400_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c400_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c400_updated_date")
    private Instant updatedDate;

    @Column(name = "c400_updated_by")
    private String updatedBy;

    @Column(name = "c400_void_fl")
    private String voidFlag;
}

