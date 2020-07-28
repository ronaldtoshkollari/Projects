package com.VideoClub.Movies.repo;

import com.VideoClub.Movies.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarksRepo extends JpaRepository<Bookmark, String> {

    @Query(value = "select movie from bookmarks where email = ?1", nativeQuery = true)
    List<String> findByEmail(String email);

}
