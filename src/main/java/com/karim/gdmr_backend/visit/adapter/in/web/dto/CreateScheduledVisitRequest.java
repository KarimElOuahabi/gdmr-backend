package com.karim.gdmr_backend.visit.adapter.in.web.dto;

public record CreateScheduledVisitRequest(Long employeeId, Long doctorId, Long timeSlotId) {}
