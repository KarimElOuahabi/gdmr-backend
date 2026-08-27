package com.karim.gdmr_backend.staff.domain.port.in;

import com.karim.gdmr_backend.staff.domain.model.StaffProfile;

public interface GetMyStaffProfileUseCase {
    StaffProfile getMyProfile(Long userId);
}
