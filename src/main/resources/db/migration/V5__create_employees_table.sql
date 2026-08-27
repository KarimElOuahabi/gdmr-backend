CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
                           birth_date DATE,
                           department_id BIGINT NOT NULL REFERENCES departments(id),
                           created_at TIMESTAMP NOT NULL DEFAULT now()
);