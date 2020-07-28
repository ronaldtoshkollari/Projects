package com.VideoClub.Movies.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BookmarkId implements Serializable {

    private String email;
    private String movie;

    public BookmarkId() {
    }

    public BookmarkId(String email, String movie) {
        this.email = email;
        this.movie = movie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }


}
