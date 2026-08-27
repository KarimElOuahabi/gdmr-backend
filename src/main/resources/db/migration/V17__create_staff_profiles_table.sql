CREATE TABLE staff_profiles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
    phone_number VARCHAR(30),
    job_title VARCHAR(100),
    hire_date DATE,
    office_location VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
