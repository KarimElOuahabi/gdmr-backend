package com.karim.gdmr_backend.visit.adapter.in.web.dto;

import com.karim.gdmr_backend.visit.domain.model.NegotiationActor;
import com.karim.gdmr_backend.visit.domain.model.NegotiationEntry;

import java.time.LocalDateTime;

public record NegotiationEntryResponse(
        Long id,
        Long visitId,
        NegotiationActor actor,
        String reason,
        LocalDateTime suggestedDateTime,
        LocalDateTime createdAt
) {
    public static NegotiationEntryResponse from(NegotiationEntry e) {
        return new NegotiationEntryResponse(e.getId(), e.getVisitId(), e.getActor(),
                e.getReason(), e.getSuggestedDateTime(), e.getCreatedAt());
    }
}
