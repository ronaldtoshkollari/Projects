package com.example.quiz.view.challenges;

import android.widget.ListView;

import com.example.quiz.domain.QuizChallenge;

import java.util.ArrayList;


public interface ChallengesView {

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Επιστρέφει τη λίστα με τις προκλήσεις που έχει στείλει.
     * @return Η λίστα με τις προκλήσεις που έχει στείλει
     */
    ListView getMyChallenges();

    /**
     * Επιστρέφει τη λίστα με τις προκλήσεις που έχει δεχτεί.
     * @return Η λίστα με τις προκλήσεις που έχει δεχτεί
     */
    ListView getIncomingChallenges();

    /**
     * Επιστρέφει τον εναπομείναντα χρόνο του χρονομέτρου.
     * @return Ο εναπομείναντας χρόνος
     */
    String getClock();

    /**
     * Θέτει τη λίστα με τις προκλήσεις που στέλνει.
     * @param myChallenges Η λίστα με τις προκλήσεις
     */
    void setMyChallenges(ArrayList<QuizChallenge> myChallenges);

    /**
     * Θέτει τη λίστα με τις προκλήσεις που δέχεται.
     * @param incomingChallenges Η λίστα με τις προκλήσεις
     */
    void setIncomingChallenges(ArrayList<QuizChallenge> incomingChallenges);

    /**
     * Θέτει το χρονόμετρο.
     * @param time Ο χρόνος που ορίζει
     */
    void setClock(String time);

    /**
     * Θέτει το όνομα χρήστη.
     */
    void setUsername();

    /**
     * Μεταφέρει τον χρήστη στο activity DualActivity
     * όταν γίνει click πάνω στην πρόκληση με id challengeId.
     * @param challengeId Το μοναδικό id της πρόκλησης
     */
    void startDualActivity(int challengeId);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
