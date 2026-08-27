package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

import java.time.LocalDateTime;

public interface RejectVisitByEmployeeUseCase {
    Visit rejectByEmployee(RejectByEmployeeCommand command);

    record RejectByEmployeeCommand(
            Long visitId,
            Long requestingEmployeeId,
            String reason,
            LocalDateTime suggestedDateTime
    ) {}
}
