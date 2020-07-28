package com.example.quiz.domain;


import java.util.LinkedList;
import java.util.List;

public class Player extends User {

    private int playerId;
    private String firstName;
    private String lastName;
    private String gender;
    private QuizCategory favoriteCategory;

    //Default Constructor

    /**
     * Κατασκευαστής
     */
    public Player(){}

    /**
     * Κατασκευαστής
     * @param playerId Το id του παίκτη
     * @param username Το όνομα χρήστη του παίκτη
     * @param password Ο κωδικός πρόσβασης του παίκτη
     * @param email Η διεύθυνση ηλεκτρονικού ταχυδρομείου του παίκτη
     * @param firstName Το πρώτο όνομα του παίκτη
     * @param lastName Τπ επώνυμο του παίκτη
     * @param gender Το γένος του παίκτη
     * @param favoriteCategory Η αγαπημένη κατηγορία του παίκτη
     */
    public Player(int playerId, String username, String password, String email, String firstName, String lastName, String gender, QuizCategory favoriteCategory) {
        super(username, password, email);
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.favoriteCategory = favoriteCategory;
    }

    /**
     * Επιστρέφει το id του παίκτη
     * @return Το id του παίκτη
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Θέτει το id του παίκτη
     * @param playerId Το id του παίκτη
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Επιστρέφει το πρώτο όνομα του παίκτη
     * @return Το πρώτο όνομα του παίκτη
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Θέτει το πρώτο όνομα του παίκτη
     * @param firstName Το πρώτο όνομα του παίκτη
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Επιστρέφει το επώνυμο του παίκτη
     * @return Το επώνυμο του παίκτη
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Θέτει το επώνυμο του παίκτη
     * @param lastName Το επώνυμο του παίκτη
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Επιστρέφει το γένος του παίκτη
     * @return Το γένος του παίκτη
     */
    public String getGender() {
        return gender;
    }

    /**
     * Θέτει το γένος του παίκτη
     * @param gender Το γένος του παίκτη
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Επιστρέφει την αγαπημένη κατηγορία του παίκτη
     * @return Η αγαπημένη κατηγορία του παίκτη
     */
    public QuizCategory getFavoriteCategory() {
        return favoriteCategory;
    }

    /**
     * Θέτει την αγαπημένη κατηγορία του παίκτη
     * @param favoriteCategory Η αγαπημένη κατηγορία του παίκτη
     */
    public void setFavoriteCategory(QuizCategory favoriteCategory) {
        this.favoriteCategory = favoriteCategory;
    }

}
