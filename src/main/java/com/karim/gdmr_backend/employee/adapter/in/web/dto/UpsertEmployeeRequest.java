package com.karim.gdmr_backend.employee.adapter.in.web.dto;

import com.karim.gdmr_backend.employee.domain.model.Department;

import java.time.LocalDate;

public record UpsertEmployeeRequest(
        LocalDate birthDate,
        Department department,
        String phoneNumber,
        String jobTitle,
        LocalDate hireDate,
        String cnssNumber
) {}
