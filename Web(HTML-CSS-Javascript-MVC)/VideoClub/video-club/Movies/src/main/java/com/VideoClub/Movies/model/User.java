package com.VideoClub.Movies.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private String email;
    private String username;

    private String password;
    private String name;
    private String surname;

    public User() {
    }

    public User(String userName) {
        this.username = userName;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
