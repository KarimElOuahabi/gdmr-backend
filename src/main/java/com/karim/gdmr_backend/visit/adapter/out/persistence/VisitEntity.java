package com.karim.gdmr_backend.visit.adapter.out.persistence;

import com.karim.gdmr_backend.visit.domain.model.VisitStatus;
import com.karim.gdmr_backend.visit.domain.model.VisitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visits")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "visit_type", nullable = false)
    private VisitType visitType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

    @ElementCollection
    @CollectionTable(name = "visit_proposed_slots", joinColumns = @JoinColumn(name = "visit_id"))
    @Column(name = "slot_date_time")
    private List<LocalDateTime> proposedSlotsByEmployee;

    @Column(name = "time_slot_id")
    private Long timeSlotId;

    @Column(name = "confirmed_date_time")
    private LocalDateTime confirmedDateTime;

    @Column(name = "motif")
    private String motif;

    @Column(name = "report_notes", columnDefinition = "TEXT")
    private String reportNotes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}