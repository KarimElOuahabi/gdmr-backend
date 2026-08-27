package com.karim.gdmr_backend.timeslot.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.visit.domain.model.VisitType;

public interface ListTimeSlotsUseCase {
    PageResult<TimeSlot> listTimeSlots(ListTimeSlotsQuery query);

    record ListTimeSlotsQuery(
            Long doctorId,
            Boolean availableOnly,
            VisitType visitType,
            int page,
            int size
    ) {}
}
