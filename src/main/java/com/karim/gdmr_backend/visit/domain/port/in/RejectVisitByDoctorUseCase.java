package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

import java.time.LocalDateTime;

public interface RejectVisitByDoctorUseCase {
    Visit rejectByDoctor(Long visitId, Long requestingDoctorId, String reason, LocalDateTime suggestedDateTime);
}
