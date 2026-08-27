package com.karim.gdmr_backend.doctor.domain.exception;

import java.time.LocalDateTime;

public class DoctorNotAvailableException extends RuntimeException {
    public DoctorNotAvailableException(Long doctorId, LocalDateTime start, LocalDateTime end) {
        super("Doctor " + doctorId + " is not available between " + start + " and " + end);
    }
}