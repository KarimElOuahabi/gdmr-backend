package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;

public interface CreateUserByAdminUseCase {
    CreatedUserResult createUser(CreateUserCommand command);

    // Inputs: No email (auto-generated), no password (backend-generated)
    record CreateUserCommand(
            String firstName    ,
            String lastName,
            Role role,
            String cin
    ) {}

    // Output: User object + unhashed temporary password to display to admin
    record CreatedUserResult(
            User user,
            String temporaryPassword
    ) {}

}
