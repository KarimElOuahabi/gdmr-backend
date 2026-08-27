package com.karim.gdmr_backend.profileissue.domain.exception;

public class ProfileIssueNotFoundException extends RuntimeException {
    public ProfileIssueNotFoundException(Long id) {
        super("Profile issue not found: " + id);
    }
}
