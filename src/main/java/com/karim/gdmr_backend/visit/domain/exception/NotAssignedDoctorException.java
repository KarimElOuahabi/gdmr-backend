package com.karim.gdmr_backend.visit.domain.exception;

public class NotAssignedDoctorException extends RuntimeException {
    public NotAssignedDoctorException(Long visitId) {
        super("You are not the doctor assigned to visit " + visitId);
    }
}
