package com.karim.gdmr_backend.profileissue.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile_issues")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileIssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reported_user_id", nullable = false)
    private Long reportedUserId;

    @Column(name = "reporter_user_id", nullable = false)
    private Long reporterUserId;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "suggested_correction", nullable = false)
    private String suggestedCorrection;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "resolved", nullable = false)
    private boolean resolved;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
