package com.karim.gdmr_backend.medicalhistory.adapter.in.web.dto;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryCategory;

public record AddMedicalHistoryEntryRequest(Long employeeId, Long visitId,
                                             MedicalHistoryCategory category, String description) {}
