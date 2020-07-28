package com.VideoClub.Movies.model;

import javax.persistence.*;

@Entity
@Table(name = "Bookmarks")
public class Bookmark {

    @EmbeddedId
    private BookmarkId bookmarkId;
    public Bookmark() {
    }

    public Bookmark(BookmarkId bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public BookmarkId getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(BookmarkId bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
