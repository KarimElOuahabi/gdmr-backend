package com.karim.gdmr_backend.notification.domain.exception;

public class UnauthorizedNotificationAccessException extends RuntimeException {
    public UnauthorizedNotificationAccessException(Long id) {
        super("You are not authorized to access notification " + id);
    }
}
