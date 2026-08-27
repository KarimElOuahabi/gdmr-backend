package com.karim.gdmr_backend.document.domain.exception;

public class MedicalDocumentNotFoundException extends RuntimeException {
    public MedicalDocumentNotFoundException(Long id) {
        super("Medical document not found: " + id);
    }
}