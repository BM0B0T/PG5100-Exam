# PG5100-exam

#### if you want to run main application it is in frontend, and you need to have a postgres server on port 5432 with password `password` for local dev

- docker run -d -u postgres -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:13





# PG5100-exam


## Innlevering

#### if you want to run main applicasion it is in frontend and you need to have a postgres server on port 5432 with password `password` for local dev

* [ ] The exam assignment will have to be zipped in a zip file with name pg5100_<id>.zip
* [ ] replace <id> with the unique id you received with these instructions
* [ ] submit all source codes (eg., .java and .xhtml), and NO compiled code (.class) or libraries (.jar, .war)
* [ ] Submit code that does compile
* [ ] DO NOT USE framworks like MOCKITO

##### R1 requirements
* [x] User: having info like name, surname, hashed-password, email, etc.
* [x] Movie: having info like title, director, year of release, etc.
* [x] Review: having info like the target movie, the actual review text, the user author, when the
  review was created, etc

You need to write Spring @Service classes to provide at least the following functionalities:

* [x] create a user
* [x] create/delete a movie
* [x] add review to a movie, with a 1 to 5 star
* [x] compute average of stars per movie
* [x] retrieve all movies, sorted by average stars
* [x] retrieve all reviews for a movie, sorted by either stars or creation time of the review

* [x] Write integration tests for each of the @Service classes, using JUnit and @SpringBootTest annotation. You
  should have at least one test for each of the public methods in those services. Enable the calculation of
  code coverage with JaCoCo.

##### R3: (Necessary but not Sufficient for C grade) Frontend

* [ ] Homepage: display all movies (info summaries). If the user is logged in, then display a welcome
  message. Each movie entry must show the average stars it has, and link to a “movie details”
  page.
* [ ] Movie details page:
    * [ ] show all reviews and their stars.
    * [ ] Give possibility to sort reviews by time and
      by stars.
    * [ ] If the user is logged in, enable possibility to write a new review. A user can only write
      one review per movie.

* [ ] User login/signup page:
    * [ ] based on Spring Security and storing of user info on the SQL database. It
    * [ ] should be possible to logout from any of the pages (e.g., via a button).
    * [ ] When a login/signup fails, you MUST show an error message.

##### R4: (Necessary but not Sufficient for B grade) Selenium Tests

testDefaultMovies:
* [ ] go to home page, and verify that at least 2 movies are displayed (there
  should be at least 2 movies by default initialized in the database).

* [ ] testWriteReview:
    1.  go to movie details page for a movie, and verify you cannot write a review for
        it.
    2. Log in. Go back to the same movie details page, and add a new review.
    3. Verify the review is visible.
    4. Log out. Verify the review is still visible.

* [ ] testStars:
  1. check current stars for a movie on home page.
  2. Write new review for it, and give a star
  which must lead to a different average.
  3.Go back to home page. Verify that the average stars for
  that movie has changed.

* [ ] testSorting:
    1. go to a movie details page, and write 3 reviews with 3 different authors, giving
       different stars.
    2. Sort the display by time and by stars, and verify it is correctly sorted.




##### Requirements:

* [ ] the delivered project must be compilable with Maven 3.x:
    * [ ] with commands like “mvn package -DskipTests”
      directly from your unzipped file

* [ ] For testing, you can use an embedded database (e.g., H2), or start a real one with Docker (or both). Endto-end tests MUST use Selenium with Chrome.





##### Instructions:

###### Assumptions:
* that the Chrome drivers are
  available under the user's home folder (as done in class and in the Git repository)

- docker run -d -u postgres -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:13