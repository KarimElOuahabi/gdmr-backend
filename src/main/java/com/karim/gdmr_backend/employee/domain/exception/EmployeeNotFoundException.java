package com.karim.gdmr_backend.employee.domain.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long userId) {
        super("Employee not found for id: " + userId);
    }
}

