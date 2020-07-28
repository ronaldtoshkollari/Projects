package com.VideoClub.Movies.service;

import com.VideoClub.Movies.model.Bookmark;
import com.VideoClub.Movies.model.User;
import com.VideoClub.Movies.repo.BookmarksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarksRepo repo;

    public List<Bookmark> listAll(){
        return repo.findAll();
    }

    public void save(Bookmark bookmark){
        repo.save(bookmark);
    }

    public Bookmark get(String email, String movie){
        return repo.findById(email).orElse(null);
    }

    public void delete(String email){
        repo.deleteById(email);
    }

    public List<String> getTitles(String email){
        return repo.findByEmail(email);
    }

}
