package com.karim.gdmr_backend.doctor.domain.port.in;

import com.karim.gdmr_backend.doctor.domain.model.Doctor;

public interface GetMyProfileUseCase {
    Doctor getMyProfile(Long userId);
}