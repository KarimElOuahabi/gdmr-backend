package com.karim.gdmr_backend.document.adapter.in.web.dto;

import com.karim.gdmr_backend.document.domain.model.DocumentType;
import com.karim.gdmr_backend.document.domain.model.MedicalDocument;

import java.time.LocalDateTime;

public record MedicalDocumentResponse(
        Long id, Long employeeId, Long visitId, DocumentType documentType,
        String originalFilename, int version, Long previousVersionId, LocalDateTime createdAt
) {
    public static MedicalDocumentResponse from(MedicalDocument d) {
        return new MedicalDocumentResponse(d.getId(), d.getEmployeeId(), d.getVisitId(), d.getDocumentType(),
                d.getOriginalFilename(), d.getVersion(), d.getPreviousVersionId(), d.getCreatedAt());
    }
}