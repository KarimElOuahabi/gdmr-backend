package com.karim.gdmr_backend.doctor.domain.port.in;

import com.karim.gdmr_backend.doctor.domain.model.Doctor;
import com.karim.gdmr_backend.doctor.domain.model.Speciality;

public interface UpsertDoctorUseCase {

    Doctor upsertDoctor(UpsertDoctorCommand command);

    record UpsertDoctorCommand(
            Long userId,
            String phoneNumber,
            Speciality specialty,
            String qualifications,
            Integer yearsOfExperience,
            String workSite,
            String cnssNumber
    ) {}
}