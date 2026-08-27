package com.karim.gdmr_backend.staff.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.staff.domain.model.StaffProfile;

import java.time.LocalDate;

public record StaffProfileResponse(
        Long userId,
        String email,
        String firstName,
        String lastName,
        Role role,
        boolean active,
        String cin,
        boolean hasProfilePicture,
        String phoneNumber,
        String jobTitle,
        LocalDate hireDate,
        String officeLocation
) {
    public static StaffProfileResponse from(User user, StaffProfile profile) {
        return new StaffProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.isActive(),
                user.getCin(),
                user.hasProfilePicture(),
                profile.getPhoneNumber(),
                profile.getJobTitle(),
                profile.getHireDate(),
                profile.getOfficeLocation()
        );
    }
}
