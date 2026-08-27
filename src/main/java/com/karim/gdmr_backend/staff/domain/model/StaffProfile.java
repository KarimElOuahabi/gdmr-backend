package com.karim.gdmr_backend.staff.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Contact/employment details for ADMIN and HR users. Unlike Employee/Doctor,
 * there's no separate management screen that creates this ahead of time — a user
 * may have no row yet, in which case callers get an "empty" profile with a null id
 * and fill it in themselves via the update use case (see StaffProfileService).
 */
@Getter
@RequiredArgsConstructor
public class StaffProfile {

    private final Long id;
    private final Long userId;
    private final String phoneNumber;
    private final String jobTitle;
    private final LocalDate hireDate;
    private final String officeLocation;

    public static StaffProfile empty(Long userId) {
        return new StaffProfile(null, userId, null, null, null, null);
    }
}
