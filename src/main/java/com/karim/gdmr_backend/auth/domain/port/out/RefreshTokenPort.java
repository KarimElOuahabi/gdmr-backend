package com.karim.gdmr_backend.auth.domain.port.out;

public interface RefreshTokenPort {
    String generateRawToken();
    String hashToken(String rawToken);
}
