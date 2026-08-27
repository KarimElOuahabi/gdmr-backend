package com.karim.gdmr_backend.timeslot.domain.exception;

public class TimeSlotNotFoundException extends RuntimeException {
    public TimeSlotNotFoundException(Long id) {
        super("TimeSlot not found: " + id);
    }
}