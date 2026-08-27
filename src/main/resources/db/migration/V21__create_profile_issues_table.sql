CREATE TABLE profile_issues (
    id BIGSERIAL PRIMARY KEY,
    reported_user_id BIGINT NOT NULL REFERENCES users(id),
    reporter_user_id BIGINT NOT NULL REFERENCES users(id),
    field_name VARCHAR(100) NOT NULL,
    suggested_correction VARCHAR(500) NOT NULL,
    note TEXT,
    resolved BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_profile_issues_reported_user ON profile_issues(reported_user_id);
