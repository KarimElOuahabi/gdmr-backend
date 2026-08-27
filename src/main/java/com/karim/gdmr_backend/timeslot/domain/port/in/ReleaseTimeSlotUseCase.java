package com.karim.gdmr_backend.timeslot.domain.port.in;

import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;

public interface ReleaseTimeSlotUseCase {
    TimeSlot releaseTimeSlot(Long timeSlotId);
}
