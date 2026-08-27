package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

public interface CreateScheduledVisitUseCase {
    Visit createScheduledVisit(CreateScheduledVisitCommand command);

    record CreateScheduledVisitCommand(Long employeeId, Long doctorId, Long timeSlotId) {}
}
