package com.karim.gdmr_backend.profileissue.adapter.in.web.dto;

import com.karim.gdmr_backend.profileissue.domain.model.ProfileIssue;

import java.time.LocalDateTime;

public record ProfileIssueResponse(
        Long id,
        String fieldName,
        String suggestedCorrection,
        String note,
        boolean resolved,
        LocalDateTime createdAt
) {
    public static ProfileIssueResponse from(ProfileIssue issue) {
        return new ProfileIssueResponse(issue.getId(), issue.getFieldName(), issue.getSuggestedCorrection(),
                issue.getNote(), issue.isResolved(), issue.getCreatedAt());
    }
}
