package com.karim.gdmr_backend.profileissue.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportProfileIssueRequest(
        @NotBlank(message = "Field name is required")
        String fieldName,

        @NotBlank(message = "Suggested correction is required")
        String suggestedCorrection,

        String note
) {}
