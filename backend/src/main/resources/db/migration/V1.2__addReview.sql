CREATE SEQUENCE review_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE reviews
(
    id                 bigint    not null,
    review_text        varchar(4096),
    rating             integer   not null check (rating <= 5 AND rating >= 1),
    timestamp          timestamp not null,
    author_username    varchar(255),
    target_movie_title varchar(255),
    primary key (id)
);


ALTER TABLE reviews
    ADD CONSTRAINT email_constraint FOREIGN KEY (author_username) REFERENCES users;
ALTER TABLE reviews
    ADD CONSTRAINT movie_title_constraint FOREIGN KEY (target_movie_title) REFERENCES movies;