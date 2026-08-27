package com.karim.gdmr_backend.doctor.domain.port.in;

public interface GetDoctorIdByUserIdUseCase {
    Long getDoctorId(Long userId);
}