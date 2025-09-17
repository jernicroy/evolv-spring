package com.evolv.care.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "t100_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c100_user_id")
    private Long id;

    @Column(name = "c100_user_name", unique = true)
    private String userName;

    @Column(name = "c100_user_sh_name")
    private String shortName;

    @Column(name = "c100_email")
    private String email;

    @Column(name = "c100_hash_code")
    private String hashCode;

    @Column(name = "c100_role")
    private String role;

    @Column(name = "c100_created_by", nullable = false)
    private String createdBy;

    @Column(name = "c100_created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "c100_updated_date")
    private Instant updatedDate;

    @Column(name = "c100_updated_by")
    private String updatedBy;

    @Column(name = "c100_void_fl")
    private String voidFl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyMemberEntity> familyMembers;
}
