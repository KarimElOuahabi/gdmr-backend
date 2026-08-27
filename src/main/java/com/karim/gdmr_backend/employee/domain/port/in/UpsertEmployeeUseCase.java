package com.karim.gdmr_backend.employee.domain.port.in;

import com.karim.gdmr_backend.employee.domain.model.Department;
import com.karim.gdmr_backend.employee.domain.model.Employee;

import java.time.LocalDate;

public interface UpsertEmployeeUseCase {
    Employee upsertEmployee(UpsertEmployeeCommand command);

    record UpsertEmployeeCommand(
            Long userId,
            LocalDate birthDate,
            Department department,
            String phoneNumber,
            String jobTitle,
            LocalDate hireDate,
            String cnssNumber
    ) {};
}
