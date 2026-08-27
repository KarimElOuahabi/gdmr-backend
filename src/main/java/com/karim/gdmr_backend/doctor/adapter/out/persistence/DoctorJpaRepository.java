package com.karim.gdmr_backend.doctor.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorJpaRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findById(Long doctorId);
    Optional<DoctorEntity> findByUserId(Long userId);
    Page<DoctorEntity> findAll(Pageable pageable);
}
