CREATE SEQUENCE review_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE reviews
(
    id           BIGINT    NOT NULL,
    review_text  VARCHAR(255) CHECK (review_text <= 100),
    rating       INTEGER   NOT NULL CHECK (rating <= 5 AND rating >= 1),
    timestamp    TIMESTAMP NOT NULL,
    author_email VARCHAR(255),
    movie_title  VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE reviews
    ADD CONSTRAINT email_constraint FOREIGN KEY (author_email) REFERENCES users;
ALTER TABLE reviews
    ADD CONSTRAINT movie_title_constraint FOREIGN KEY (movie_title) REFERENCES movies;