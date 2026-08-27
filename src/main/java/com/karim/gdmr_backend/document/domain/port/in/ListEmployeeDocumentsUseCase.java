package com.karim.gdmr_backend.document.domain.port.in;

import com.karim.gdmr_backend.document.domain.model.MedicalDocument;

import java.util.List;

public interface ListEmployeeDocumentsUseCase {
    List<MedicalDocument> listForEmployee(Long employeeId);
}