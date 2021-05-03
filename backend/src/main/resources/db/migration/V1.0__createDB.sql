CREATE TABLE users
(
    username      VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) CHECK (last_name >= 2 AND last_name <= 32),
    first_name VARCHAR(255) CHECK (first_name >= 2 AND first_name <= 32),
    password   VARCHAR(255),
    PRIMARY KEY (username)
);
