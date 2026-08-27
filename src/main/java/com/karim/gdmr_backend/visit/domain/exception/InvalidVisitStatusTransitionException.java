package com.karim.gdmr_backend.visit.domain.exception;

import com.karim.gdmr_backend.visit.domain.model.VisitStatus;

public class InvalidVisitStatusTransitionException extends RuntimeException {
    public InvalidVisitStatusTransitionException(VisitStatus from, VisitStatus to) {
        super("Cannot transition visit from " + from + " to " + to);
    }
}