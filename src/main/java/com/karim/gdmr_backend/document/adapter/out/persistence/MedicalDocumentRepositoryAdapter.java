package com.karim.gdmr_backend.document.adapter.out.persistence;

import com.karim.gdmr_backend.document.domain.model.MedicalDocument;
import com.karim.gdmr_backend.document.domain.port.out.MedicalDocumentRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MedicalDocumentRepositoryAdapter implements MedicalDocumentRepositoryPort {

    private final MedicalDocumentJpaRepository jpaRepository;

    public MedicalDocumentRepositoryAdapter(MedicalDocumentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MedicalDocument save(MedicalDocument document) {
        MedicalDocumentEntity entity = new MedicalDocumentEntity(
                document.getId(), document.getEmployeeId(), document.getVisitId(), document.getDocumentType(),
                document.getOriginalFilename(), document.getStorageKey(), document.getUploadedByUserId(),
                document.getVersion(), document.getPreviousVersionId(), LocalDateTime.now());
        MedicalDocumentEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<MedicalDocument> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<MedicalDocument> findAllByEmployeeId(Long employeeId) {
        return jpaRepository.findAllByEmployeeIdOrderByCreatedAtDesc(employeeId).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    private MedicalDocument toDomain(MedicalDocumentEntity e) {
        return new MedicalDocument(e.getId(), e.getEmployeeId(), e.getVisitId(), e.getDocumentType(),
                e.getOriginalFilename(), e.getStorageKey(), e.getUploadedByUserId(),
                e.getVersion(), e.getPreviousVersionId(), e.getCreatedAt());
    }
}