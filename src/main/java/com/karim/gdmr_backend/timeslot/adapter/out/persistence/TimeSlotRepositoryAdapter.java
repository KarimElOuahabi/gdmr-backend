package com.karim.gdmr_backend.timeslot.adapter.out.persistence;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.timeslot.adapter.out.persistence.TimeSlotEntity;
import com.karim.gdmr_backend.timeslot.adapter.out.persistence.TimeSlotJpaRepository;
import com.karim.gdmr_backend.timeslot.domain.model.TimeSlot;
import com.karim.gdmr_backend.timeslot.domain.port.in.ListTimeSlotsUseCase.ListTimeSlotsQuery;
import com.karim.gdmr_backend.timeslot.domain.port.out.TimeSlotRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TimeSlotRepositoryAdapter implements TimeSlotRepositoryPort {

    private final TimeSlotJpaRepository jpaRepository;

    public TimeSlotRepositoryAdapter(TimeSlotJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TimeSlot save(TimeSlot creneau) {
        TimeSlotEntity entity = new TimeSlotEntity(
                creneau.getId(), creneau.getDoctorId(), creneau.getStartTime(),
                creneau.getEndTime(), creneau.getVisitType(), creneau.isAvailable(),
                LocalDateTime.now());
        TimeSlotEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<TimeSlot> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public PageResult<TimeSlot> findAllPaged(ListTimeSlotsQuery query) {
        var pageable = PageRequest.of(query.page(), query.size(), Sort.by(Sort.Direction.ASC, "startTime"));
        var page = jpaRepository.search(query.doctorId(),
                query.availableOnly() != null && query.availableOnly(), query.visitType(), pageable);

        List<TimeSlot> content = page.getContent().stream().map(this::toDomain).toList();
        return new PageResult<>(content, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public boolean existsOverlapping(Long doctorId, LocalDateTime startTime, LocalDateTime endTime) {
        return jpaRepository.existsOverlapping(doctorId, startTime, endTime);
    }

    private TimeSlot toDomain(TimeSlotEntity entity) {
        return new TimeSlot(entity.getId(), entity.getDoctorId(), entity.getStartTime(),
                entity.getEndTime(), entity.getVisitType(), entity.isAvailable());
    }
}