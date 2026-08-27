package com.karim.gdmr_backend.auth.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @Email @NotBlank String email,
        @NotBlank String password,
        String firstName,
        String lastName,
        Role role
) {}
