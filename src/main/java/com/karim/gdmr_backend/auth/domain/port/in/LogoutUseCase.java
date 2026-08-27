package com.karim.gdmr_backend.auth.domain.port.in;

public interface LogoutUseCase {
    void logout(String rawRefreshToken);
}
