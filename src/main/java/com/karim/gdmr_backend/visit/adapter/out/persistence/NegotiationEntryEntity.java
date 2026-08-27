package com.karim.gdmr_backend.visit.adapter.out.persistence;

import com.karim.gdmr_backend.visit.domain.model.NegotiationActor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_negotiation_entries")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NegotiationEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "actor_role", nullable = false)
    private NegotiationActor actor;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(name = "suggested_date_time", nullable = false)
    private LocalDateTime suggestedDateTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
