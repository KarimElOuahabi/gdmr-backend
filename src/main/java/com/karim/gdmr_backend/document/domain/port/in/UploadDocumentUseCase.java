package com.karim.gdmr_backend.document.domain.port.in;

import com.karim.gdmr_backend.document.domain.model.DocumentType;
import com.karim.gdmr_backend.document.domain.model.MedicalDocument;
import org.springframework.web.multipart.MultipartFile;

public interface UploadDocumentUseCase {

    MedicalDocument upload(UploadDocumentCommand command);

    record UploadDocumentCommand(
            Long employeeId,
            Long visitId,
            DocumentType documentType,
            MultipartFile file,
            Long uploadedByUserId,
            String uploaderRole,
            Long previousVersionId
    ) {}
}