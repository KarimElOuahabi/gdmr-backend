package com.karim.gdmr_backend.auth.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        Role role,
        boolean active,
        String cin,
        boolean hasProfilePicture
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole(), user.isActive(), user.getCin(), user.hasProfilePicture());
    }
}