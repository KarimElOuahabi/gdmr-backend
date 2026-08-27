package com.karim.gdmr_backend.staff.domain.port.in;

import com.karim.gdmr_backend.staff.domain.model.StaffProfile;

import java.time.LocalDate;

public interface UpdateMyStaffProfileUseCase {
    StaffProfile updateMyProfile(UpdateStaffProfileCommand command);

    record UpdateStaffProfileCommand(
            Long userId,
            String phoneNumber,
            String jobTitle,
            LocalDate hireDate,
            String officeLocation
    ) {}
}
