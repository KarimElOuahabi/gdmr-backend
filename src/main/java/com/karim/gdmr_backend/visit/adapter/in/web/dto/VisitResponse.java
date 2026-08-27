package com.karim.gdmr_backend.visit.adapter.in.web.dto;

import com.karim.gdmr_backend.visit.domain.model.VisitType;
import com.karim.gdmr_backend.visit.domain.model.Visit;
import com.karim.gdmr_backend.visit.domain.model.VisitStatus;

import java.time.LocalDateTime;
import java.util.List;

public record VisitResponse(
        Long id,
        Long employeeId,
        Long doctorId,
        VisitType visitType,
        VisitStatus status,
        List<LocalDateTime> proposedSlotsByEmployee,
        Long timeSlotId,
        LocalDateTime confirmedDateTime,
        String motif,
        String reportNotes
) {
    public static VisitResponse fullAccess(Visit v) {
        return new VisitResponse(v.getId(), v.getEmployeeId(), v.getDoctorId(),
                v.getVisitType(), v.getStatus(), v.getProposedSlotsByEmployee(),
                v.getTimeSlotId(), v.getConfirmedDateTime(), v.getMotif(), v.getReportNotes());
    }

    public static VisitResponse administrativeView(Visit v) {
        return new VisitResponse(v.getId(), v.getEmployeeId(), v.getDoctorId(),
                v.getVisitType(), v.getStatus(), v.getProposedSlotsByEmployee(),
                v.getTimeSlotId(), v.getConfirmedDateTime(), v.getMotif(), null);
    }
}
