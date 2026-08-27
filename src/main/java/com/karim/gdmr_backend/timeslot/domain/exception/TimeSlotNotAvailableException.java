package com.karim.gdmr_backend.timeslot.domain.exception;

public class TimeSlotNotAvailableException extends RuntimeException {
    public TimeSlotNotAvailableException(Long id) {
        super("TimeSlot " + id + " is no longer available");
    }
}