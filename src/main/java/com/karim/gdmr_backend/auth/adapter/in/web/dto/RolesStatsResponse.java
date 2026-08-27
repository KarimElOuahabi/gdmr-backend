package com.karim.gdmr_backend.auth.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.port.in.GetRolesStatsUseCase;

public record RolesStatsResponse(Role role, long totalUsers, long activeUsers, long inactiveUsers) {
    public static RolesStatsResponse from(GetRolesStatsUseCase.RolesStats stats) {
        return new RolesStatsResponse(stats.role(), stats.totalUsers(), stats.activeUsers(), stats.inactiveUsers());
    }
}