package com.karim.gdmr_backend.staff.adapter.in.web.dto;

import java.time.LocalDate;

public record UpdateStaffProfileRequest(
        String phoneNumber,
        String jobTitle,
        LocalDate hireDate,
        String officeLocation
) {}
