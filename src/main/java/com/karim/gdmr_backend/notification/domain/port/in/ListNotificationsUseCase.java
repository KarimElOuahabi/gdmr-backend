package com.karim.gdmr_backend.notification.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.notification.domain.model.Notification;
import com.karim.gdmr_backend.notification.domain.model.NotificationType;

import java.util.List;

public interface ListNotificationsUseCase {
    PageResult<Notification> listForUser(ListNotificationsQuery query);

    record ListNotificationsQuery(Long userId, Boolean unreadOnly, List<NotificationType> types, int page, int size) {}
}
