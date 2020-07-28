package com.example.quiz.ViewModels.domain;

import com.example.quiz.domain.User;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testLogInOut() {
        User user = new User("Jim","Beq","jimisbek@hotmail.com");


    }

    @Test
    public void memberFunctions(){
        User user = new User();

        user.setUsername("Roni");
        assertEquals("Roni", user.getUsername());

        user.setEmail("roni@email.com");
        assertEquals("roni@email.com", user.getEmail());

        user.setPassword("123");
        assertEquals("123", user.getPassword());
    }

    @Test
    public void registerTest(){
        User user = new User("Jim","Beq","jimisbek@hotmail.com");

    }

    @Test
    public void modifyTest() {
        User user = new User("Jim","Beq","jimisbek@hotmail.com");

    }

    @Test
    public void deleteAccount(){
        User user = new User();

        user.setEmail("roni@email.com");

    }

}