package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.AuthTokens;

public interface LoginUseCase {
    AuthTokens login(String email, String rawPassword);
}
