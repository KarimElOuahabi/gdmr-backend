package com.karim.gdmr_backend.notification.domain.port.in;

import com.karim.gdmr_backend.notification.domain.model.Notification;

public interface MarkNotificationReadUseCase {
    Notification markRead(Long notificationId, Long requestingUserId);
    void markAllRead(Long userId);
}
