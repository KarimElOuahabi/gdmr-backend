package com.karim.gdmr_backend.medicalhistory.domain.port.in;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryCategory;
import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;

public interface AddMedicalHistoryEntryUseCase {

    MedicalHistoryEntry addEntry(AddMedicalHistoryEntryCommand command);

    record AddMedicalHistoryEntryCommand(Long employeeId, Long doctorId, Long visitId,
                                          MedicalHistoryCategory category, String description) {}
}
