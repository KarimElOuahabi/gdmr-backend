package com.karim.gdmr_backend.timeslot.domain.port.in;

import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;

public interface BookTimeSlotUseCase {
    TimeSlot bookTimeSlot(Long timeSlotId, Long expectedDoctorId);
}
