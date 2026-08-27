package com.karim.gdmr_backend.employee.domain.exception;

import com.karim.gdmr_backend.auth.domain.model.Role;

public class InvalidRoleForEmployeeException extends  RuntimeException {
    public InvalidRoleForEmployeeException(Role role) {
        super("Cannot create an Employee profile for a user with role " + role);
    }
}
