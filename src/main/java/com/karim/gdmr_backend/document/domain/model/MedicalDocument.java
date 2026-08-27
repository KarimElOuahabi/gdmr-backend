package com.karim.gdmr_backend.document.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MedicalDocument {

    private final Long id;
    private final Long employeeId;
    private final Long visitId;
    private final DocumentType documentType;
    private final String originalFilename;
    private final String storageKey;
    private final Long uploadedByUserId;
    private final int version;
    private final Long previousVersionId;
    private final LocalDateTime createdAt;

    public static MedicalDocument createNew(Long employeeId, Long visitId, DocumentType documentType,
                                            String originalFilename, String storageKey,
                                            Long uploadedByUserId, int version, Long previousVersionId) {
        return new MedicalDocument(null, employeeId, visitId, documentType, originalFilename,
                storageKey, uploadedByUserId, version, previousVersionId, LocalDateTime.now());
    }
}