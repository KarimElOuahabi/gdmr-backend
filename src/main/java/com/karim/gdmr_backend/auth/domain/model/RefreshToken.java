package com.karim.gdmr_backend.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.Instant;


@Getter
@RequiredArgsConstructor
public class RefreshToken {

    private final Long id;
    private final Long userId;
    private final String tokenHash;
    private final Instant expiresAt;
    private final boolean revoked;

    public static RefreshToken createNew(Long userId, String tokenHash, Instant expiresAt) {
        return new RefreshToken(null, userId, tokenHash, expiresAt, false);
    }

    public boolean isValid() {
        return !revoked && Instant.now().isBefore(expiresAt);
    }

}