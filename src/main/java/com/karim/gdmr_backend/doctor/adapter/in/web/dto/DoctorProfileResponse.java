package com.karim.gdmr_backend.doctor.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.doctor.domain.model.Doctor;
import com.karim.gdmr_backend.doctor.domain.model.Speciality;

public record DoctorProfileResponse(
        Long id,
        Long userId,
        String email,
        String firstName,
        String lastName,
        Role role,
        boolean active,
        String cin,
        boolean hasProfilePicture,
        String phoneNumber,
        Speciality specialty,
        String qualifications,
        Integer yearsOfExperience,
        String workSite,
        String cnssNumber
) {
    public static DoctorProfileResponse from(User user, Doctor doctor) {
        return new DoctorProfileResponse(
                doctor.getId(),
                doctor.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.isActive(),
                user.getCin(),
                user.hasProfilePicture(),
                doctor.getPhoneNumber(),
                doctor.getSpecialty(),
                doctor.getQualifications(),
                doctor.getYearsOfExperience(),
                doctor.getWorkSite(),
                doctor.getCnssNumber()
        );
    }
}
