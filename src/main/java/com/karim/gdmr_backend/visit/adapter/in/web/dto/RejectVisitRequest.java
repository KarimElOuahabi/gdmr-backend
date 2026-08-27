package com.karim.gdmr_backend.visit.adapter.in.web.dto;

import java.time.LocalDateTime;

public record RejectVisitRequest(String reason, LocalDateTime suggestedDateTime) {}
