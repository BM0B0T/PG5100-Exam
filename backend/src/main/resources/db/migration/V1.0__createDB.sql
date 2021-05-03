CREATE TABLE users
(
    email      VARCHAR(255) NOT NULL CHECK (email >= 3 AND email <= 32),
    last_name  VARCHAR(255) CHECK (last_name >= 2 AND last_name <= 32),
    first_name VARCHAR(255) CHECK (first_name >= 2 AND first_name <= 32),
    password   VARCHAR(255),
    PRIMARY KEY (email)
);
