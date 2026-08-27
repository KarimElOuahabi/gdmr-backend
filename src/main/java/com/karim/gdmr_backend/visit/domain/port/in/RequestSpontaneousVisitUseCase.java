package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestSpontaneousVisitUseCase {

    Visit requestVisit(RequestVisitCommand command);

    record RequestVisitCommand(Long employeeId, Long doctorId, String motif, List<LocalDateTime> proposedSlots) {}
}