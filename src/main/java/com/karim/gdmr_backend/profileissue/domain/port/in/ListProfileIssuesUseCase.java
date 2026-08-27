package com.karim.gdmr_backend.profileissue.domain.port.in;

import com.karim.gdmr_backend.profileissue.domain.model.ProfileIssue;

import java.util.List;

public interface ListProfileIssuesUseCase {
    List<ProfileIssue> listForUser(Long reportedUserId);
}
