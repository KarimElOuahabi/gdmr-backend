CREATE TABLE time_slots (
                            id BIGSERIAL PRIMARY KEY,
                            doctor_id BIGINT NOT NULL REFERENCES doctors(id),
                            start_time TIMESTAMP NOT NULL,
                            end_time TIMESTAMP NOT NULL,
                            visit_type VARCHAR(50) NOT NULL,
                            available BOOLEAN NOT NULL DEFAULT TRUE,
                            created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_time_slots_doctor_id ON time_slots(doctor_id);
CREATE INDEX idx_time_slots_available ON time_slots(available);

CREATE INDEX idx_time_slots_doctor_schedule ON time_slots(doctor_id, start_time, end_time);