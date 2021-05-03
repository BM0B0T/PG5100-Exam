
CREATE SEQUENCE entity_id_sequence START 1 INCREMENT 1;
CREATE TABLE users
(
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);
