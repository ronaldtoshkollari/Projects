package com.example.quiz.view.home;

public interface HomeView {

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Θέτει το όνομα χρήστη.
     */
    void setUsername();

    /**
     * Μεταφέρει τον χρήστη στο activity CreateQuizActivity
     * όταν γίνει click πάνω στο κουμπί ΔΗΜΙΟΥΡΓΙΑ QUIZ.
     */
    void startCreateQuizActivity();

    /**
     * Μεταφέρει τον χρήστη στο activity ChallengesActivity
     * όταν γίνει click πάνω στο κουμπί ΑΠΑΝΤΗΣΗ ΠΡΟΚΛΗΣΗΣ.
     */
    void startChallengesActivity();

    /**
     * Μεταφέρει τον χρήστη στο activity ShowRankActivity
     * όταν γίνει click πάνω στο κουμπί ΚΑΤΑΤΑΞΕΙΣ.
     */
    void startShowRankActivity();

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String message);
}
