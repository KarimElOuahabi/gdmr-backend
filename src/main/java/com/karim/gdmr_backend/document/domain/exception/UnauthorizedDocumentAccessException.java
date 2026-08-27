package com.karim.gdmr_backend.document.domain.exception;

public class UnauthorizedDocumentAccessException extends RuntimeException {
    public UnauthorizedDocumentAccessException(Long documentId) {
        super("You are not authorized to access document " + documentId);
    }
}