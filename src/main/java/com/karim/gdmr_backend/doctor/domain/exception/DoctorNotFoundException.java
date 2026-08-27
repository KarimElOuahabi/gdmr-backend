package com.karim.gdmr_backend.doctor.domain.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(Long userId) {
        super("Doctor not found for id: " + userId);
    }
}
