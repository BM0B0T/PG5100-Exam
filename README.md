# PG5100-exam

### if you want to run main application it is in frontend, and you need to have a postgres server on port 5432 with password `password` for local dev

- OBS: not to be used in production!!
- `docker run -d -u postgres -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:13`

#### Entry point is `frontend/src/test/java/org/tsdes/frontend/LocalApplicationRunner.java`

### tasks

- I did requirements R1, R2, R3, R4 and R5.

### Solution

- port is `8080`.
- is\`t a website made with Spring Boot and JSF.
- it\'s possible to see all movies on `/index.jsf` or `/`.
- you can select one, and see all reviews on that movie and sort it by rating and newest.
- if you log in you can review a movie you haven't reviewed yet.

### Entity`s

- changed user.name to firstName to make it work with sql, because name is a reserved word in sql.

### Extra feature

- you are able to add movie to the Database if you are logged in. have selenium test for it `addNewMovie`.
- added support for dates to movies to make it more accurate.




