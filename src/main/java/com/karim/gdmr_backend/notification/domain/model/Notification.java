package com.karim.gdmr_backend.notification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Notification {

    private final Long id;
    private final Long recipientUserId;
    private final NotificationType type;
    private final String title;
    private final String message;
    private final Long relatedVisitId;
    private final Long relatedUserId;
    private final boolean read;
    private final LocalDateTime createdAt;

    public static Notification createNew(
            Long recipientUserId, NotificationType type, String title, String message,
            Long relatedVisitId, Long relatedUserId) {
        return new Notification(null, recipientUserId, type, title, message,
                relatedVisitId, relatedUserId, false, LocalDateTime.now());
    }

    public Notification withRead(boolean read) {
        return new Notification(id, recipientUserId, type, title, message,
                relatedVisitId, relatedUserId, read, createdAt);
    }
}
