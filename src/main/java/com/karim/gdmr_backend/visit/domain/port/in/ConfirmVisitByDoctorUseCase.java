package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.Visit;

public interface ConfirmVisitByDoctorUseCase {
    Visit confirmByDoctor(Long visitId, Long requestingDoctorId);
}
