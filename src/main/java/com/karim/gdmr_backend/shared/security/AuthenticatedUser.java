package com.karim.gdmr_backend.shared.security;

public record AuthenticatedUser(Long userId, String email) {}