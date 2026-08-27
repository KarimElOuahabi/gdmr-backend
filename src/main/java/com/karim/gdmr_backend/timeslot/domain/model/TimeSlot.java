package com.karim.gdmr_backend.timeslot.domain.model;

import com.karim.gdmr_backend.visit.domain.model.VisitType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class TimeSlot {

    private final Long id;
    private final Long doctorId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final VisitType visitType;
    private final boolean available;

    public static TimeSlot createNew(Long doctorId, LocalDateTime startTime,
                                     LocalDateTime endTime, VisitType visitType) {
        return new TimeSlot(null, doctorId, startTime, endTime, visitType, true);
    }

    public TimeSlot markAsBooked() {
        return new TimeSlot(id, doctorId, startTime, endTime, visitType, false);
    }

    public TimeSlot markAsAvailable() {
        return new TimeSlot(id, doctorId, startTime, endTime, visitType, true);
    }
}