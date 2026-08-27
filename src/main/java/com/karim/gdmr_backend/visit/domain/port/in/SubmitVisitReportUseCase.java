package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

public interface SubmitVisitReportUseCase {

    Visit submitReport(SubmitReportCommand command);

    record SubmitReportCommand(
            Long visitId,
            String reportNotes,
            Long requestingDoctorId
    ) {}
}