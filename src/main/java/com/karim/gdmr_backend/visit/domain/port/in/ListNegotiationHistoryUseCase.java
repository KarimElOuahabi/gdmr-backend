package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.visit.domain.model.NegotiationEntry;

import java.util.List;

public interface ListNegotiationHistoryUseCase {
    List<NegotiationEntry> listHistory(Long visitId);
}
