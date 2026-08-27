package com.karim.gdmr_backend.visit.adapter.out.persistence;

import com.karim.gdmr_backend.visit.domain.model.NegotiationEntry;
import com.karim.gdmr_backend.visit.domain.port.out.NegotiationEntryRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NegotiationEntryRepositoryAdapter implements NegotiationEntryRepositoryPort {

    private final NegotiationEntryJpaRepository jpaRepository;

    public NegotiationEntryRepositoryAdapter(NegotiationEntryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public NegotiationEntry save(NegotiationEntry entry) {
        NegotiationEntryEntity entity = new NegotiationEntryEntity(
                entry.getId(),
                entry.getVisitId(),
                entry.getActor(),
                entry.getReason(),
                entry.getSuggestedDateTime(),
                LocalDateTime.now()
        );
        NegotiationEntryEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<NegotiationEntry> findAllByVisitId(Long visitId) {
        return jpaRepository.findAllByVisitIdOrderByCreatedAtAsc(visitId).stream()
                .map(this::toDomain)
                .toList();
    }

    private NegotiationEntry toDomain(NegotiationEntryEntity entity) {
        return new NegotiationEntry(
                entity.getId(),
                entity.getVisitId(),
                entity.getActor(),
                entity.getReason(),
                entity.getSuggestedDateTime(),
                entity.getCreatedAt()
        );
    }
}
