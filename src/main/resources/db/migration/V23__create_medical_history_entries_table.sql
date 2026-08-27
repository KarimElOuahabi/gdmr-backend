CREATE TABLE medical_history_entries (
                                          id BIGSERIAL PRIMARY KEY,
                                          employee_id BIGINT NOT NULL REFERENCES employees(id),
                                          doctor_id BIGINT NOT NULL REFERENCES doctors(id),
                                          visit_id BIGINT REFERENCES visits(id),
                                          category VARCHAR(50) NOT NULL,
                                          description TEXT NOT NULL,
                                          created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_medical_history_entries_employee_id ON medical_history_entries(employee_id);
