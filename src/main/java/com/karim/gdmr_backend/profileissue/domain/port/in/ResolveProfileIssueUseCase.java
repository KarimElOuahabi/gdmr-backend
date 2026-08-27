package com.karim.gdmr_backend.profileissue.domain.port.in;

import com.karim.gdmr_backend.profileissue.domain.model.ProfileIssue;

public interface ResolveProfileIssueUseCase {
    ProfileIssue setResolved(Long issueId, boolean resolved);
}
