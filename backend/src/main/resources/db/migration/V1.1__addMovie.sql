CREATE TABLE movies
(
    title           varchar(1024) not null,
    director        varchar(1024),
    date_of_release date          not null,
    primary key (title)
)