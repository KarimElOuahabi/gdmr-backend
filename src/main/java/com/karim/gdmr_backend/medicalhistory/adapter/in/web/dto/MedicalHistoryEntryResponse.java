package com.karim.gdmr_backend.medicalhistory.adapter.in.web.dto;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryCategory;
import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;

import java.time.LocalDateTime;

public record MedicalHistoryEntryResponse(Long id, Long employeeId, Long doctorId, Long visitId,
                                           MedicalHistoryCategory category, String description,
                                           LocalDateTime createdAt) {

    public static MedicalHistoryEntryResponse from(MedicalHistoryEntry entry) {
        return new MedicalHistoryEntryResponse(
                entry.getId(), entry.getEmployeeId(), entry.getDoctorId(), entry.getVisitId(),
                entry.getCategory(), entry.getDescription(), entry.getCreatedAt());
    }
}
