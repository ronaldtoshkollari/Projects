package com.example.quiz.view.create_quiz;

public interface CreateQuizChallengeView {

    /**
     * Επιστρέφει το όνομα χρήστη του αντιπάλου.
     * @return Το όνομα χρήστη του αντιπάλου
     */
    String getOpponent();

    /**
     * Επιστρέφει το επίπεδο δυσκολίας των ερωτήσεων του quiz.
     * @return Το επίπεδο δυσκολίας των ερωτήσεων του quiz
     */
    String getDifficultyLevel();

    /**
     * Επιστρέφει την κατηγορία των ερωτήσεων του quiz.
     * @return Την κατηγορία των ερωτήσεων του quiz
     */
    String getCategory();

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Θέτει το όνομα χρήστη του αντιπάλου
     * @param opponent Το όνομα χρήστη του αντιπάλου
     */
    void setOpponent(String opponent);

    /**
     * Θέτει το επίπεδο δυσκολίας των ερωτήσεων του quiz.
     * @param difficultyLevel Το επίπεδο δυσκολίας των ερωτήσεων του quiz
     */
    void setDifficultyLevel(String difficultyLevel);

    /**
     * Θέτει την κατηγορία των ερωτήσεων του quiz.
     * @param category Η κατηγορία των ερωτήσεων του quiz
     */
    void setCategory(String category);

    /**
     * Θέτει το όνομα χρήστη.
     */
    void setUsername();

    /**
     * Μεταφέρει τον χρήστη στο activity ChallengesActivity
     * όταν γίνει click πάνω στην πρόκληση με id challengeId.
     */
    void startChallengesActivity();

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
