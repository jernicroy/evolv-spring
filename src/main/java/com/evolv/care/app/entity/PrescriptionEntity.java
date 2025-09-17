package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "t600_prescription")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c600_prescription_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c600_family_member_id", nullable = false)
    private FamilyMemberEntity familyMember;

    @Column(name = "c600_file_path", nullable = false)
    private String filePath;

    @Column(name = "c600_uploaded_at", nullable = false)
    private Instant uploadedAt = Instant.now();

    @Column(name = "c600_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c600_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c600_updated_date")
    private Instant updatedDate;

    @Column(name = "c600_updated_by")
    private String updatedBy;

    @Column(name = "c600_void_fl")
    private String voidFlag;

}
