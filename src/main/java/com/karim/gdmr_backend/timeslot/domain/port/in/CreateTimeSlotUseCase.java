package com.karim.gdmr_backend.timeslot.domain.port.in;

import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.visit.domain.model.VisitType;

import java.time.LocalDateTime;

public interface CreateTimeSlotUseCase {
    TimeSlot createTimeSlot(CreateTimeSlotCommand command);

    record CreateTimeSlotCommand(
            Long doctorId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            VisitType visitType
    ) {}
}
