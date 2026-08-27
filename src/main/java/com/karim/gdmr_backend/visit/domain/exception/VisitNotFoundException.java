package com.karim.gdmr_backend.visit.domain.exception;

public class VisitNotFoundException extends RuntimeException {
    public VisitNotFoundException(Long id) {
        super("Visit not found: " + id);
    }
}
