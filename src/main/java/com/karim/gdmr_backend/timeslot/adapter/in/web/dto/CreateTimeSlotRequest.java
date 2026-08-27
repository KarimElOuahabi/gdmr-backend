package com.karim.gdmr_backend.timeslot.adapter.in.web.dto;

import com.karim.gdmr_backend.visit.domain.model.VisitType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTimeSlotRequest(
        @NotNull Long doctorId,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotNull VisitType visitType
) {}