package com.VideoClub.Movies.repo;

import com.VideoClub.Movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
