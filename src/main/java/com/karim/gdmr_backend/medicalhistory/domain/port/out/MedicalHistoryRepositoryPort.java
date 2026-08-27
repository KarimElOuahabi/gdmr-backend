package com.karim.gdmr_backend.medicalhistory.domain.port.out;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;

import java.util.List;

public interface MedicalHistoryRepositoryPort {
    MedicalHistoryEntry save(MedicalHistoryEntry entry);
    List<MedicalHistoryEntry> findAllByEmployeeId(Long employeeId);
}
