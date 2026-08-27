-- 1. Rename and modify the column on the main table
ALTER TABLE visits RENAME COLUMN proposed_date_time TO confirmed_date_time;
ALTER TABLE visits ALTER COLUMN confirmed_date_time DROP NOT NULL;

-- 2. Create the new table and index for employee-proposed slots
CREATE TABLE visit_proposed_slots (
                                       id BIGSERIAL PRIMARY KEY,
                                       visit_id BIGINT NOT NULL REFERENCES visits(id) ON DELETE CASCADE,
                                       slot_date_time TIMESTAMP NOT NULL
);

CREATE INDEX idx_visit_proposed_slots_visit_id ON visit_proposed_slots(visit_id);