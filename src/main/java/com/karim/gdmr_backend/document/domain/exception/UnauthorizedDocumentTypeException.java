package com.karim.gdmr_backend.document.domain.exception;

import com.karim.gdmr_backend.document.domain.model.DocumentType;

public class UnauthorizedDocumentTypeException extends RuntimeException {
    public UnauthorizedDocumentTypeException(DocumentType type, String role) {
        super("Role " + role + " cannot upload documents of type " + type);
    }
}