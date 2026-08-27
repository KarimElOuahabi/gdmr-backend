package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.Role;

import java.util.List;

public interface GetRolesStatsUseCase {
    List<RolesStats> getRolesStats();

    record RolesStats(
            Role role,
            long totalUsers,
            long activeUsers,
            long inactiveUsers
    ) {}
}
