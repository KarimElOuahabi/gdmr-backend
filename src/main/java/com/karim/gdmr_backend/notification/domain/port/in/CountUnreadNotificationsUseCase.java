package com.karim.gdmr_backend.notification.domain.port.in;

public interface CountUnreadNotificationsUseCase {
    long countUnread(Long userId);
}
