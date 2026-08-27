CREATE TABLE specialities (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE doctors (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
                         phone_number VARCHAR(20),
                         specialty_id BIGINT REFERENCES specialities(id),
                         qualifications VARCHAR(255),
                         years_of_experience INTEGER,
                         work_site VARCHAR(150),
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO specialities (name) VALUES
                                    ('OCCUPATIONAL_PHYSICIAN'),
                                    ('GENERAL_PRACTITIONER'),
                                    ('OPHTHALMOLOGIST'),
                                    ('DENTIST'),
                                    ('PSYCHOLOGIST'),
                                    ('ERGONOMIST'),
                                    ('OTHER');