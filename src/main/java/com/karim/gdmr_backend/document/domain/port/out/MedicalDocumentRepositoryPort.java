package com.karim.gdmr_backend.document.domain.port.out;

import com.karim.gdmr_backend.document.domain.model.MedicalDocument;

import java.util.List;
import java.util.Optional;

public interface MedicalDocumentRepositoryPort {
    MedicalDocument save(MedicalDocument document);
    Optional<MedicalDocument> findById(Long id);
    List<MedicalDocument> findAllByEmployeeId(Long employeeId);
    void deleteById(Long id);
}