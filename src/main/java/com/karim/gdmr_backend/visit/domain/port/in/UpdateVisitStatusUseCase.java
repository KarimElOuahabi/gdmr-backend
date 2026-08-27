package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;
import com.karim.gdmr_backend.visit.domain.model.VisitStatus;

public interface UpdateVisitStatusUseCase {

    Visit updateStatus(UpdateVisitStatusCommand command);

    record UpdateVisitStatusCommand(
            Long visitId,
            VisitStatus newStatus,
            Long requestingDoctorId
    ) {}
}