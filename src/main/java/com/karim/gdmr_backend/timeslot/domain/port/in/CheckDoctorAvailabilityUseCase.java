package com.karim.gdmr_backend.timeslot.domain.port.in;

import java.time.LocalDateTime;

public interface CheckDoctorAvailabilityUseCase {
    boolean isDoctorAvailable(Long doctorId, LocalDateTime startTime, LocalDateTime endTime);
}
