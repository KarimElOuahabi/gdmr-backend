package com.karim.gdmr_backend.medicalhistory.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryJpaRepository extends JpaRepository<MedicalHistoryEntity, Long> {
    List<MedicalHistoryEntity> findAllByEmployeeIdOrderByCreatedAtDesc(Long employeeId);
}
