ALTER TABLE visits
    ADD COLUMN time_slot_id BIGINT NULL REFERENCES time_slots(id);
