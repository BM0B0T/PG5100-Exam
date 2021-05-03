CREATE TABLE movies
(
    title           VARCHAR(255) NOT NULL CHECK (title <= 32),
    director        VARCHAR(255) CHECK (director >= 4 AND director <= 64),
    year_of_release INTEGER      NOT NULL,
    PRIMARY KEY (title)
);