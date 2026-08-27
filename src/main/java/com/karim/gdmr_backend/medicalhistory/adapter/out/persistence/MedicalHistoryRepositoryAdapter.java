package com.karim.gdmr_backend.medicalhistory.adapter.out.persistence;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;
import com.karim.gdmr_backend.medicalhistory.domain.port.out.MedicalHistoryRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalHistoryRepositoryAdapter implements MedicalHistoryRepositoryPort {

    private final MedicalHistoryJpaRepository jpaRepository;

    public MedicalHistoryRepositoryAdapter(MedicalHistoryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MedicalHistoryEntry save(MedicalHistoryEntry entry) {
        MedicalHistoryEntity entity = new MedicalHistoryEntity(
                entry.getId(), entry.getEmployeeId(), entry.getDoctorId(), entry.getVisitId(),
                entry.getCategory(), entry.getDescription(), entry.getCreatedAt());
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<MedicalHistoryEntry> findAllByEmployeeId(Long employeeId) {
        return jpaRepository.findAllByEmployeeIdOrderByCreatedAtDesc(employeeId).stream()
                .map(this::toDomain)
                .toList();
    }

    private MedicalHistoryEntry toDomain(MedicalHistoryEntity entity) {
        return new MedicalHistoryEntry(
                entity.getId(), entity.getEmployeeId(), entity.getDoctorId(), entity.getVisitId(),
                entity.getCategory(), entity.getDescription(), entity.getCreatedAt());
    }
}
