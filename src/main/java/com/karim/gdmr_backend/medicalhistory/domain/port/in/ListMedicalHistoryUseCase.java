package com.karim.gdmr_backend.medicalhistory.domain.port.in;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;

import java.util.List;

public interface ListMedicalHistoryUseCase {
    List<MedicalHistoryEntry> listForEmployee(Long employeeId);
}
