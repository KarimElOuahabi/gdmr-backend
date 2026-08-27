package com.karim.gdmr_backend.doctor.adapter.in.web.dto;

import com.karim.gdmr_backend.doctor.domain.model.Doctor;
import com.karim.gdmr_backend.doctor.domain.model.Speciality;

public record DoctorResponse(
        Long id,
        Long userId,
        String phoneNumber,
        Speciality specialty,
        String qualifications,
        Integer yearsOfExperience,
        String workSite,
        String cnssNumber
) {
    public static DoctorResponse from(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getUserId(),
                doctor.getPhoneNumber(),
                doctor.getSpecialty(),
                doctor.getQualifications(),
                doctor.getYearsOfExperience(),
                doctor.getWorkSite(),
                doctor.getCnssNumber()
        );
    }
}
