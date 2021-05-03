CREATE TABLE users
(
    username   varchar(1024) not null,
    last_name  varchar(1024),
    first_name varchar(1024),
    password   varchar(1024),
    primary key (username)
);
