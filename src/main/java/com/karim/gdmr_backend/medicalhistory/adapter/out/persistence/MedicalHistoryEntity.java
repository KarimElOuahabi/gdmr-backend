package com.karim.gdmr_backend.medicalhistory.adapter.out.persistence;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_history_entries")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "visit_id")
    private Long visitId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedicalHistoryCategory category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
