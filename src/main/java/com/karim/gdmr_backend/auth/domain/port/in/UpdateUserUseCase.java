package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(UpdateUserCommand command);

    record UpdateUserCommand(
            Long userId,
            String firstName,
            String lastName,
            Role role,
            String cin
    ) {}
}
