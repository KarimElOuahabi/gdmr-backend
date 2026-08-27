package com.karim.gdmr_backend.medicalhistory.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MedicalHistoryEntry {

    private final Long id;
    private final Long employeeId;
    private final Long doctorId;
    private final Long visitId;
    private final MedicalHistoryCategory category;
    private final String description;
    private final LocalDateTime createdAt;

    public static MedicalHistoryEntry createNew(Long employeeId, Long doctorId, Long visitId,
                                                 MedicalHistoryCategory category, String description) {
        return new MedicalHistoryEntry(null, employeeId, doctorId, visitId, category, description, LocalDateTime.now());
    }
}
