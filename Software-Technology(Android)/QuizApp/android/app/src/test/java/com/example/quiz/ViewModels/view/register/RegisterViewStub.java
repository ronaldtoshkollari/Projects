package com.example.quiz.ViewModels.view.register;

import com.example.quiz.view.register.RegisterView;

public class RegisterViewStub implements RegisterView {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String favouriteCategory;
    private String gender;
    private String toastMessage;
    private int startHomeActivityClick;
    private int startLoginClick;

    /**
     * Επιστέφει το όνομα χρήστη
     * @return Το όνομα χρήστη
     */
    @Override
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
     * Επιστέφει τον κωδικό πρόσβασης του χρήστη
     * @return Τον κωδικό πρόσβασης του χρήστη
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Θέτει τον κωδικό πρόσβασης του χρήστη
     * @param password Τον κωδικό πρόσβασης του χρήστη
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Επιστέφει τη διεύθυνση ηλεκτρονικού ταχυδρομείου του χρήστη
     * @return Τη διεύθυνση ηλεκτρονικού ταχυδρομείου του χρήστη
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Θέτει τη διεύθυνση ηλεκτρονικού ταχυδρομείου του χρήστη
     * @param email Τη διεύθυνση ηλεκτρονικού ταχυδρομείου του χρήστη
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Επιστέφει το πρώτο όνομα του χρήστη
     * @return Το πρώτο όνομα του χρήστη
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Θέτει το πρώτο όνομα του χρήστη
     * @param firstName Το πρώτο όνομα του χρήστη
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Επιστέφει το επώνυμο του χρήστη
     * @return Το επώνυμο του χρήστη
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Θέτει το επώνυμο του χρήστη
     * @param lastName Το επώνυμο του χρήστη
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Επιστέφει την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     * @return Την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     */
    @Override
    public String getFavouriteCategory() {
        return favouriteCategory;
    }

    /**
     * Θέτει την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     * @param favouriteCategory Την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     */
    public void setFavouriteCategory(String favouriteCategory) {
        this.favouriteCategory = favouriteCategory;
    }

    /**
     * Επιστέφει το γένος του χρήστη
     * @return Το γένος του χρήστη
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * Θέτει το γένος του χρήστη
     * @param gender Το γένος του χρήστη
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String message) {
        this.toastMessage = message;
    }

    /**
     * Επιστρέφει ένα Toast.
     * @return Το περιεχόμενο που θα εμφανιστεί
     */
    public String getToastMessage() {
        return toastMessage;
    }

    /**
     * Αυξάνει τα click για το activity HomeActivity κατά ένα
     */
    @Override
    public void startHomeActivity() {
        startHomeActivityClick++;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity HomeActivity
     */
    public int getStartHomeActivityClick() {
        return startHomeActivityClick;
    }

    /**
     * Αυξάνει τα click για το activity Login κατά ένα
     */
    @Override
    public void startLogin() {
        startLoginClick++;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity Login
     */
    public int getStartLoginClick() {
        return startLoginClick;
    }
}
