package com.karim.gdmr_backend.auth.adapter.in.web.dto;

public record CreateUserResponse(UserResponse user, String temporaryPassword) {}