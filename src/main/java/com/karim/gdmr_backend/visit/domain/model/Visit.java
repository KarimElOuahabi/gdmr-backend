package com.karim.gdmr_backend.visit.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Visit {

    private final Long id;
    private final Long employeeId;
    private final Long doctorId;
    private final VisitType visitType;
    private final VisitStatus status;
    private final List<LocalDateTime> proposedSlotsByEmployee;
    private final Long timeSlotId;
    private final LocalDateTime confirmedDateTime;
    private final String motif;
    private final String reportNotes;

    public static Visit createNew(
            Long employeeId,
            Long doctorId,
            String motif,
            List<LocalDateTime> slots
    ) {
        return new Visit(
                null,
                employeeId,
                doctorId,
                VisitType.SPONTANEOUS,
                VisitStatus.REQUESTED,
                slots,
                null,
                null,
                motif,
                null
        );
    }

    public static Visit createScheduled(
            Long employeeId,
            Long doctorId,
            VisitType visitType,
            Long timeSlotId,
            LocalDateTime confirmedDateTime
    ) {
        return new Visit(
                null,
                employeeId,
                doctorId,
                visitType,
                VisitStatus.PROPOSED,
                List.of(),
                timeSlotId,
                confirmedDateTime,
                null,
                null
        );
    }

    public Visit withStatus(VisitStatus newStatus) {
        return new Visit(
                id,
                employeeId,
                doctorId,
                visitType,
                newStatus,
                proposedSlotsByEmployee,
                timeSlotId,
                confirmedDateTime,
                motif,
                reportNotes);
    }

    public Visit withAssignedSlot(Long timeSlotId, LocalDateTime dateTime) {
        return new Visit(
                id,
                employeeId,
                doctorId,
                visitType,
                VisitStatus.PROPOSED,
                proposedSlotsByEmployee,
                timeSlotId,
                dateTime,
                motif,
                reportNotes);
    }

    public Visit withReleasedSlot() {
        return new Visit(
                id,
                employeeId,
                doctorId,
                visitType,
                VisitStatus.REQUESTED,
                proposedSlotsByEmployee,
                null,
                null,
                motif,
                reportNotes);
    }

    public Visit withReport(String notes) {
        return new Visit(
                id,
                employeeId,
                doctorId,
                visitType,
                VisitStatus.COMPLETED,
                proposedSlotsByEmployee,
                timeSlotId,
                confirmedDateTime,
                motif,
                notes);
    }
}
