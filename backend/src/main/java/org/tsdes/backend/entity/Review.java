package org.tsdes.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(generator = "review_id_sequence")
    private Long id;

    @ManyToOne
    private Movie targetMovie;

    @Size(min = 1, max = 4096)
    @NotBlank
    private String ReviewText;

    @ManyToOne
    private User Author;

    @Min(1)
    @Max(5)
    private int rating;

    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    public Long getId() {
        return id;
    }

    public Movie getTargetMovie() {
        return targetMovie;
    }

    public void setTargetMovie(Movie targetMovie) {
        this.targetMovie = targetMovie;
    }

    public String getReviewText() {
        return ReviewText;
    }

    public void setReviewText(String reviewText) {
        ReviewText = reviewText;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
