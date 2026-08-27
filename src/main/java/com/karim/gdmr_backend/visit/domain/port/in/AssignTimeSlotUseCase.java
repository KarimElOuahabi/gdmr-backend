package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

public interface AssignTimeSlotUseCase {
    Visit assignTimeSlot(AssignTimeSlotCommand command);

    record AssignTimeSlotCommand(Long visitId, Long timeSlotId) {}
}
