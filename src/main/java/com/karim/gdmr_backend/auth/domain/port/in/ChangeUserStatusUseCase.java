package com.karim.gdmr_backend.auth.domain.port.in;

public interface ChangeUserStatusUseCase {

    void changeUserStatus(ChangeUserStatusCommand command);

    record ChangeUserStatusCommand(
            Long userId,
            boolean active
    ) {}
}
