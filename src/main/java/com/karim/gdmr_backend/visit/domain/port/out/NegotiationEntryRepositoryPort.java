package com.karim.gdmr_backend.visit.domain.port.out;

import com.karim.gdmr_backend.visit.domain.model.NegotiationEntry;

import java.util.List;

public interface NegotiationEntryRepositoryPort {
    NegotiationEntry save(NegotiationEntry entry);
    List<NegotiationEntry> findAllByVisitId(Long visitId);
}
