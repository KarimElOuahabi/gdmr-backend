package com.karim.gdmr_backend.doctor.domain.exception;

import com.karim.gdmr_backend.auth.domain.model.Role;

public class InvalidRoleForDoctorException extends RuntimeException {
    public InvalidRoleForDoctorException(Role role) {
        super("User role " + role + " cannot be assigned a doctor profile");
    }
}
