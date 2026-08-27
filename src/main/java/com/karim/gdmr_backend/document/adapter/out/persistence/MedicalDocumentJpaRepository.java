package com.karim.gdmr_backend.document.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalDocumentJpaRepository extends JpaRepository<MedicalDocumentEntity, Long> {
    List<MedicalDocumentEntity> findAllByEmployeeIdOrderByCreatedAtDesc(Long employeeId);
}