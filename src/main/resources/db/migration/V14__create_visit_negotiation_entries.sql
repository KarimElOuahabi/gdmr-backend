CREATE TABLE visit_negotiation_entries (
    id BIGSERIAL PRIMARY KEY,
    visit_id BIGINT NOT NULL REFERENCES visits(id),
    actor_role VARCHAR(20) NOT NULL,
    reason TEXT NOT NULL,
    suggested_date_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_negotiation_visit_id ON visit_negotiation_entries(visit_id);

ALTER TABLE visits
    DROP COLUMN rejection_reason;
