package com.karim.gdmr_backend.notification.adapter.in.web.dto;

import com.karim.gdmr_backend.notification.domain.model.Notification;
import com.karim.gdmr_backend.notification.domain.model.NotificationType;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        NotificationType type,
        String title,
        String message,
        Long relatedVisitId,
        Long relatedUserId,
        boolean read,
        LocalDateTime createdAt
) {
    public static NotificationResponse from(Notification n) {
        return new NotificationResponse(n.getId(), n.getType(), n.getTitle(), n.getMessage(),
                n.getRelatedVisitId(), n.getRelatedUserId(), n.isRead(), n.getCreatedAt());
    }
}
