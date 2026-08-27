package com.karim.gdmr_backend.auth.domain.model;

public record UserPrincipal(
        Long userId,
        String email,
        Role role
) {}