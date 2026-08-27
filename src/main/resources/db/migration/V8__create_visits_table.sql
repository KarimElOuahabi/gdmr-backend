CREATE TABLE visits (
                        id BIGSERIAL PRIMARY KEY,
                        time_slot_id BIGINT REFERENCES time_slots(id),
                        employee_id BIGINT NOT NULL REFERENCES employees(id),
                        doctor_id BIGINT NOT NULL REFERENCES doctors(id),
                        visit_type VARCHAR(50) NOT NULL,
                        status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
                        scheduled_at TIMESTAMP NOT NULL,
                        motif VARCHAR(500),
                        report_notes TEXT,
                        created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_visits_employee_id ON visits(employee_id);
CREATE INDEX idx_visits_doctor_id ON visits(doctor_id);
CREATE INDEX idx_visits_status ON visits(status);