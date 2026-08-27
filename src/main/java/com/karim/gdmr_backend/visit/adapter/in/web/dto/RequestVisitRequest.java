package com.karim.gdmr_backend.visit.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RequestVisitRequest(String motif, List<LocalDateTime> proposedSlots) {
}
