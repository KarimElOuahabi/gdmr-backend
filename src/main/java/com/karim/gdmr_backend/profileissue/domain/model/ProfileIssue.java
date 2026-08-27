package com.karim.gdmr_backend.profileissue.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProfileIssue {

    private final Long id;
    private final Long reportedUserId;
    private final Long reporterUserId;
    private final String fieldName;
    private final String suggestedCorrection;
    private final String note;
    private final boolean resolved;
    private final LocalDateTime createdAt;

    public static ProfileIssue createNew(Long reportedUserId, Long reporterUserId, String fieldName,
                                          String suggestedCorrection, String note) {
        return new ProfileIssue(null, reportedUserId, reporterUserId, fieldName,
                suggestedCorrection, note, false, LocalDateTime.now());
    }

    public ProfileIssue withResolved(boolean resolved) {
        return new ProfileIssue(id, reportedUserId, reporterUserId, fieldName,
                suggestedCorrection, note, resolved, createdAt);
    }
}
