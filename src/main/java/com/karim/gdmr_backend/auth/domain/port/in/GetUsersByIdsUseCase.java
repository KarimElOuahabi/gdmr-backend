package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.User;

import java.util.List;

public interface GetUsersByIdsUseCase {
    List<User> getUsersByIds(List<Long> ids);
}