package com.example.quiz.view.register;

public interface RegisterView {

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Επιστρέφει τον κωδικό πρόσβασης.
     * @return Τον κωδικό πρόσβασης
     */
    String getPassword();

    /**
     * Επιστρέφει την διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @return Την διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    String getEmail();

    /**
     * Επιστρέφει το πρώτο όνομα του χρήστη.
     * @return Το πρώτο όνομα του χρήστη
     */
    String getFirstName();

    /**
     * Επιστρέφει το επώνυμο του χρήστη.
     * @return Το επώνυμο του χρήστη
     */
    String getLastName();

    /**
     * Επιστρέφει την αγαπημένη κατηγορία ερωτήσεων του χρήστη.
     * @return Την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     */
    String getFavouriteCategory();

    /**
     * Επιστρέφει το γένος του χρήστη.
     * @return Το γένος του χρήστη
     */
    String getGender();

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String message);

    /**
     * Μεταφέρει τον χρήστη στο activity HomeActivity
     * όταν γίνει click πάνω στο κουμπί ΕΓΓΡΑΦΗ.
     */
    void startHomeActivity();

    /**
     * Μεταφέρει τον χρήστη στο activity LoginActivity
     * όταν γίνει click πάνω στο κουμπί Είσοδος.
     */
    void startLogin();

}
