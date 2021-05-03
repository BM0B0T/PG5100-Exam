package org.tsdes.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Table(name = "MOVIES")
public class Movie {

    @Size(min = 1, max = 1024)
    @Id
    @NotBlank
    private String title;

    @Size(min = 1, max = 1024)
    @NotBlank
    private String director;

    @NotNull
    private Date yearOfRelease;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Date yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
