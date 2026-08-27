package com.karim.gdmr_backend.employee.adapter.in.web.dto;

import com.karim.gdmr_backend.employee.domain.model.Department;
import com.karim.gdmr_backend.employee.domain.model.Employee;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        Long userId,
        LocalDate birthDate,
        Department department,
        String phoneNumber,
        String jobTitle,
        LocalDate hireDate,
        String cnssNumber
) {
    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getUserId(),
                employee.getBirthDate(),
                employee.getDepartment(),
                employee.getPhoneNumber(),
                employee.getJobTitle(),
                employee.getHireDate(),
                employee.getCnssNumber()
        );
    }
}
