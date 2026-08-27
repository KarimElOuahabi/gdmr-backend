ALTER TABLE visits DROP COLUMN IF EXISTS time_slot_id;
ALTER TABLE visits RENAME COLUMN scheduled_at TO proposed_date_time;
ALTER TABLE visits ALTER COLUMN proposed_date_time DROP NOT NULL;
ALTER TABLE visits ALTER COLUMN status SET DEFAULT 'REQUESTED';