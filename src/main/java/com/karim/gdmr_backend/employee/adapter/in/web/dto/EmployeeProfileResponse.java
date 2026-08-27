package com.karim.gdmr_backend.employee.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.employee.domain.model.Department;
import com.karim.gdmr_backend.employee.domain.model.Employee;

import java.time.LocalDate;

public record EmployeeProfileResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role,
        boolean active,
        String cin,
        boolean hasProfilePicture,
        Long employeeId,
        LocalDate birthDate,
        Department department,
        String phoneNumber,
        String jobTitle,
        LocalDate hireDate,
        String cnssNumber
) {
    public static EmployeeProfileResponse from(User user, Employee employee) {
        return new EmployeeProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.isActive(),
                user.getCin(),
                user.hasProfilePicture(),
                employee.getId(),
                employee.getBirthDate(),
                employee.getDepartment(),
                employee.getPhoneNumber(),
                employee.getJobTitle(),
                employee.getHireDate(),
                employee.getCnssNumber()
        );
    }
}
