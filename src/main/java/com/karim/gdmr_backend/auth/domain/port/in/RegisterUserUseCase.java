package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;

public interface RegisterUserUseCase {
    User register(String email, String rawPassword, String firstName, String lastName, Role role);
}
