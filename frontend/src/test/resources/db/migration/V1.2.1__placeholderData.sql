INSERT INTO users(username, last_name, first_name, password)
VALUES ('admin@gmail.com', 'password1', 'anonymous', 'is watching you');

INSERT INTO movies(title, director, date_of_release)
VALUES ('Deadpool', 'Tim Miller', '2016-02-12');
INSERT INTO movies(title, director, date_of_release)
VALUES ('Deadpool 2', 'David Leitch', '2018-06-12');
INSERT INTO movies(title, director, date_of_release)
VALUES ('V for Vendetta', 'James McTeigue', '2006-03-17');
INSERT INTO movies(title, director, date_of_release)
VALUES ('Snowden', 'Oliver Stone', '2016-09-16');
INSERT INTO movies(title, director, date_of_release)
VALUES ('Limitless', 'Neil Burger', '2011-03-18');
INSERT INTO movies(title, director, date_of_release)
VALUES ('The Godfather', 'Francis Ford Coppola', '1972-03-24');
INSERT INTO movies(title, director, date_of_release)
VALUES ('Iron Man', 'Jon Favreau', '2008-05-02');



INSERT INTO reviews(id, review_text, rating, timestamp, author_username, target_movie_title)
VALUES (9223372036854775807, 'Good', 5, '2021-05-03 00:00:00-00', 'admin@gmail.com', 'Snowden');

