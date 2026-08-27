package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.AuthTokens;

public interface RefreshAccessTokenUseCase {
    AuthTokens refresh(String rawRefreshToken);
}
