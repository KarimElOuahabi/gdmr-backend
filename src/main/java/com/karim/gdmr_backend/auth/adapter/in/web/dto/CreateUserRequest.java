package com.karim.gdmr_backend.auth.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.doctor.domain.model.Speciality;
import com.karim.gdmr_backend.employee.domain.model.Department;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record CreateUserRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Role is required")
        Role role,

        String cin,

        @Valid EmployeeInfo employeeInfo,
        @Valid DoctorInfo doctorInfo,
        @Valid StaffInfo staffInfo
) {
    public record EmployeeInfo(
            @NotNull(message = "Birth date is required")
            LocalDate birthDate,

            @NotNull(message = "Department is required")
            Department department,

            String phoneNumber,
            String jobTitle,
            LocalDate hireDate,
            String cnssNumber
    ) {}

    public record DoctorInfo(
            @NotBlank(message = "Phone number is required")
            String phoneNumber,

            @NotNull(message = "Specialty is required")
            Speciality specialty,

            @NotBlank(message = "Qualifications are required")
            String qualifications,

            @NotNull(message = "Years of experience is required")
            @PositiveOrZero(message = "Years of experience cannot be negative")
            Integer yearsOfExperience,

            @NotBlank(message = "Work site is required")
            String workSite,

            String cnssNumber
    ) {}

    public record StaffInfo(
            String phoneNumber,
            String jobTitle,
            LocalDate hireDate,
            String officeLocation
    ) {}
}