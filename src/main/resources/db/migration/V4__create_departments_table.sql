CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO departments (name) VALUES
                                   ('DEVELOPMENT'),
                                   ('INFRASTRUCTURE_DEVOPS'),
                                   ('CONSULTING'),
                                   ('TECHNICAL_SUPPORT'),
                                   ('QUALITY_ASSURANCE'),
                                   ('HUMAN_RESOURCES'),
                                   ('FINANCE_ACCOUNTING'),
                                   ('SALES'),
                                   ('MARKETING'),
                                   ('EXECUTIVE_MANAGEMENT');