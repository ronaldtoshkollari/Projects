package com.VideoClub.Movies.service;

import com.VideoClub.Movies.model.User;
import com.VideoClub.Movies.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user){
        repo.save(user);
    }

    public User get(String email){
        return repo.findById(email).orElse(null);
    }

    public void delete(String email){
        repo.deleteById(email);
    }

}
