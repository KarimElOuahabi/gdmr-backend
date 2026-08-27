package com.karim.gdmr_backend.timeslot.adapter.out.persistence;

import com.karim.gdmr_backend.visit.domain.model.VisitType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TimeSlotJpaRepository extends JpaRepository<TimeSlotEntity, Long> {

    @Query("""
        SELECT c FROM TimeSlotEntity c
        WHERE (:doctorId IS NULL OR c.doctorId = :doctorId)
        AND (:availableOnly = FALSE OR c.available = TRUE)
        AND (:visitType IS NULL OR c.visitType = :visitType)
        """)
    Page<TimeSlotEntity> search(@Param("doctorId") Long doctorId,
                               @Param("availableOnly") boolean availableOnly,
                               @Param("visitType") VisitType visitType,
                               Pageable pageable);

    @Query("""
        SELECT COUNT(c) > 0 FROM TimeSlotEntity c
        WHERE c.doctorId = :doctorId
        AND c.startTime < :endTime
        AND c.endTime > :startTime
        """)
    boolean existsOverlapping(@Param("doctorId") Long doctorId,
                              @Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);
}