INSERT INTO movies(title, director, year_of_release)
VALUES ('Deadpool', 'Tim Miller', '2016-02-12');
INSERT INTO movies(title, director, year_of_release)
VALUES ('Deadpool 2', 'David Leitch', '2018-06-12');


INSERT INTO users(username, last_name, first_name, password)
VALUES ('test@test.com', 'password1', 'Test', 'Åss');


/* gjør det på en annen måte*/
INSERT INTO reviews(id, review_text, rating, timestamp, author_username, target_movie_title)
VALUES(9223372036854775805,'something special',5,'2021-05-03 19:10:25-07','test@test.com','Deadpool');
INSERT INTO reviews(id, review_text, rating, timestamp, author_username, target_movie_title)
VALUES (9223372036854775806,'something special',2,'2021-05-03 19:11:25-07','test@test.com','Deadpool');
INSERT INTO reviews(id, review_text, rating, timestamp, author_username, target_movie_title)
VALUES (9223372036854775807,'something special',4,'2021-05-04 19:10:25-07','test@test.com','Deadpool');

