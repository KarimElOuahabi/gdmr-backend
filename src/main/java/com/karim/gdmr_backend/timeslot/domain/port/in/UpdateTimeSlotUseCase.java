package com.karim.gdmr_backend.timeslot.domain.port.in;

import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;

import java.time.LocalDateTime;

public interface UpdateTimeSlotUseCase {

    TimeSlot updateTimeSlot(UpdateTimeSlotCommand command);

    record UpdateTimeSlotCommand(
            Long timeSlotId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {}
}
