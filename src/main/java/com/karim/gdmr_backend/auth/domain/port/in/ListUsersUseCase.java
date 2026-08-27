package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;

public interface ListUsersUseCase {
    PageResult<User> listUsers(ListUsersQuery query);

    record ListUsersQuery(
            String search,
            Role role,
            Boolean active,
            int page,
            int size
    ) {}
}
