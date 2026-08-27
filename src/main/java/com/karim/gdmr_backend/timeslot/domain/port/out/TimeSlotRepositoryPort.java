package com.karim.gdmr_backend.timeslot.domain.port.out;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.timeslot.domain.port.in.ListTimeSlotsUseCase.ListTimeSlotsQuery;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeSlotRepositoryPort {
    TimeSlot save(TimeSlot timeSlot);
    Optional<TimeSlot> findById(Long id);
    PageResult<TimeSlot> findAllPaged(ListTimeSlotsQuery query);
    boolean existsOverlapping(Long doctorId, LocalDateTime startTime, LocalDateTime endTime);
}

