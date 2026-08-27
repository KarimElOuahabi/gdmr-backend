package com.karim.gdmr_backend.visit.domain.exception;

public class UnauthorizedVisitAccessException extends RuntimeException {
    public UnauthorizedVisitAccessException(Long visitId) {
        super("You are not authorized to view visit " + visitId);
    }
}
