package com.karim.gdmr_backend.visit.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationEntryJpaRepository extends JpaRepository<NegotiationEntryEntity, Long> {
    List<NegotiationEntryEntity> findAllByVisitIdOrderByCreatedAtAsc(Long visitId);
}
