package com.karim.gdmr_backend.employee.domain.port.in;

import com.karim.gdmr_backend.employee.domain.model.Employee;

public interface GetEmployeeByIdUseCase {
    Employee getEmployeeById(Long employeeId);
}
