package com.karim.gdmr_backend.visit.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class NegotiationEntry {

    private final Long id;
    private final Long visitId;
    private final NegotiationActor actor;
    private final String reason;
    private final LocalDateTime suggestedDateTime;
    private final LocalDateTime createdAt;

    public static NegotiationEntry createNew(
            Long visitId, NegotiationActor actor, String reason, LocalDateTime suggestedDateTime) {
        return new NegotiationEntry(null, visitId, actor, reason, suggestedDateTime, LocalDateTime.now());
    }
}
