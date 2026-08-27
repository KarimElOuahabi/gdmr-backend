CREATE TABLE medical_documents (
                                   id BIGSERIAL PRIMARY KEY,
                                   employee_id BIGINT NOT NULL REFERENCES employees(id),
                                   visit_id BIGINT REFERENCES visits(id),
                                   document_type VARCHAR(50) NOT NULL,
                                   original_filename VARCHAR(255) NOT NULL,
                                   storage_key VARCHAR(500) NOT NULL,
                                   uploaded_by_user_id BIGINT NOT NULL REFERENCES users(id),
                                   version INTEGER NOT NULL DEFAULT 1,
                                   previous_version_id BIGINT REFERENCES medical_documents(id),
                                   created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_medical_documents_employee_id ON medical_documents(employee_id);