package com.karim.gdmr_backend.auth.domain.port.out;

import com.karim.gdmr_backend.auth.domain.model.User;

public interface TokenGeneratorPort {
    String generateToken(User user);
}
