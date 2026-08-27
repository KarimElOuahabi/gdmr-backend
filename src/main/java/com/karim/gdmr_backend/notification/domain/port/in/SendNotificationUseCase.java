package com.karim.gdmr_backend.notification.domain.port.in;

import com.karim.gdmr_backend.notification.domain.model.Notification;
import com.karim.gdmr_backend.notification.domain.model.NotificationType;

public interface SendNotificationUseCase {
    Notification send(SendNotificationCommand command);
    boolean alreadySent(Long visitId, NotificationType type);

    record SendNotificationCommand(
            Long recipientUserId,
            NotificationType type,
            String title,
            String message,
            Long relatedVisitId,
            Long relatedUserId
    ) {
        public SendNotificationCommand(Long recipientUserId, NotificationType type, String title,
                                        String message, Long relatedVisitId) {
            this(recipientUserId, type, title, message, relatedVisitId, null);
        }
    }
}
