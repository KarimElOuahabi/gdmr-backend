package com.karim.gdmr_backend.employee.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Employee {

    private final Long id;
    private final Long userId;
    private final LocalDate birthDate;
    private final Department department;
    private final String phoneNumber;
    private final String jobTitle;
    private final LocalDate hireDate;
    private final String cnssNumber;

    public static Employee createNew(Long id, Long userId, LocalDate birthDate, Department department,
                                      String phoneNumber, String jobTitle, LocalDate hireDate, String cnssNumber) {
        return new Employee(null, userId, birthDate, department, phoneNumber, jobTitle, hireDate, cnssNumber);
    }

}
