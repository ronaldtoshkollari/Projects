package com.example.quiz.domain;

public class User {

    protected String username; //the real username that user has
    protected String password; //the real password that user has
    protected String email; //the real email that user has

    /**
     * Κατασκευαστής
     */
    public User() {}

    /**
     * Κατασκευαστής
     * @param username Το όνομα χρήστη
     * @param password Ο κωδικός πρόσβασης
     * @param email Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Επιστρέφει το όνομα χρήστη
     * @return Το όνομα χρήστη
     */
    public String getUsername() {
        return username;
    }

    /**
     * Θέτει το όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Επιστρέφει τον κωδικό πρόσβασης
     * @return Ο κωδικός πρόσβασης
     */
    public String getPassword() {
        return password;
    }

    /**
     * Θέτει τον κωδικό πρόσβασης
     * @param password Ο κωδικός πρόσβασης
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Επιστρέφει την διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @return Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    public String getEmail() {
        return email;
    }

    /**
     * Θέτει την διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @param email Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
