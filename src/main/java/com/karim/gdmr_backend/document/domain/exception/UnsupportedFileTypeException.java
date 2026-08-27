package com.karim.gdmr_backend.document.domain.exception;

public class UnsupportedFileTypeException extends RuntimeException {
    public UnsupportedFileTypeException(String contentType) {
        super("Unsupported file type: " + contentType + ". Only PDF, JPG, and PNG are accepted.");
    }
}
