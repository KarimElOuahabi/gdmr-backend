CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       role_id BIGINT NOT NULL REFERENCES roles(id),
                       created_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO roles (name) VALUES
    ('ADMIN'),
    ('HR'),
    ('DOCTOR'),
    ('EMPLOYEE');

