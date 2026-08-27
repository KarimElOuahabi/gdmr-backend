package com.karim.gdmr_backend.employee.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.employee.domain.model.Employee;

public interface ListEmployeesUseCase {
    PageResult<Employee> listEmployees(ListEmployeesQuery query);

    record ListEmployeesQuery(String search, int page, int size) {}
}