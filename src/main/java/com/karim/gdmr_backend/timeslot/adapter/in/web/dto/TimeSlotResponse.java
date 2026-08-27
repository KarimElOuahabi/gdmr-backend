package com.karim.gdmr_backend.timeslot.adapter.in.web.dto;

import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.visit.domain.model.VisitType;

import java.time.LocalDateTime;

public record TimeSlotResponse(
        Long id, Long doctorId, LocalDateTime startTime,
        LocalDateTime endTime, VisitType visitType, boolean available
) {
    public static TimeSlotResponse from(TimeSlot c) {
        return new TimeSlotResponse(c.getId(), c.getDoctorId(), c.getStartTime(),
                c.getEndTime(), c.getVisitType(), c.isAvailable());
    }
}