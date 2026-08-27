package com.karim.gdmr_backend.timeslot.application;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.doctor.domain.exception.DoctorNotAvailableException;
import com.karim.gdmr_backend.timeslot.domain.exception.TimeSlotNotAvailableException;
import com.karim.gdmr_backend.timeslot.domain.exception.TimeSlotNotFoundException;
import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.timeslot.domain.port.in.BookTimeSlotUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.in.CheckDoctorAvailabilityUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.in.CreateTimeSlotUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.in.ListTimeSlotsUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.in.ReleaseTimeSlotUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.in.UpdateTimeSlotUseCase;
import com.karim.gdmr_backend.timeslot.domain.port.out.TimeSlotRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class TimeSlotService implements CreateTimeSlotUseCase, UpdateTimeSlotUseCase,
        ListTimeSlotsUseCase, CheckDoctorAvailabilityUseCase, BookTimeSlotUseCase, ReleaseTimeSlotUseCase {

    private final TimeSlotRepositoryPort timeSlotRepository;

    public TimeSlotService(TimeSlotRepositoryPort timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public TimeSlot createTimeSlot(CreateTimeSlotCommand command) {
        if (!isDoctorAvailable(command.doctorId(), command.startTime(), command.endTime())) {
            throw new DoctorNotAvailableException(command.doctorId(), command.startTime(), command.endTime());
        }

        TimeSlot createNew = new TimeSlot(
                null,
                command.doctorId(),
                command.startTime(),
                command.endTime(),
                command.visitType(),
                true
        );
        return timeSlotRepository.save(createNew);
    }

    @Override
    public TimeSlot updateTimeSlot(UpdateTimeSlotCommand command) {
        TimeSlot existing = timeSlotRepository.findById(command.timeSlotId())
                .orElseThrow(() -> new TimeSlotNotFoundException(command.timeSlotId()));

        TimeSlot updated = new TimeSlot(
                existing.getId(),
                existing.getDoctorId(),
                command.startTime(),
                command.endTime(),
                existing.getVisitType(),
                existing.isAvailable()
        );
        return timeSlotRepository.save(updated);
    }

    @Override
    public PageResult<TimeSlot> listTimeSlots(ListTimeSlotsQuery query) {
        return timeSlotRepository.findAllPaged(query);
    }

    @Override
    public boolean isDoctorAvailable(Long doctorId, LocalDateTime startTime, LocalDateTime endTime) {
        return !timeSlotRepository.existsOverlapping(doctorId, startTime, endTime);
    }

    @Override
    public TimeSlot bookTimeSlot(Long timeSlotId, Long expectedDoctorId) {
        TimeSlot existing = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new TimeSlotNotFoundException(timeSlotId));

        if (!existing.getDoctorId().equals(expectedDoctorId)) {
            throw new IllegalArgumentException(
                    "TimeSlot " + timeSlotId + " does not belong to doctor " + expectedDoctorId);
        }
        if (!existing.isAvailable()) {
            throw new TimeSlotNotAvailableException(timeSlotId);
        }

        return timeSlotRepository.save(existing.markAsBooked());
    }

    @Override
    public TimeSlot releaseTimeSlot(Long timeSlotId) {
        TimeSlot existing = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new TimeSlotNotFoundException(timeSlotId));

        return timeSlotRepository.save(existing.markAsAvailable());
    }
}