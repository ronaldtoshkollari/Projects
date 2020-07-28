package com.example.quiz.ViewModels.view.challenges;

import android.widget.ListView;

import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.view.challenges.ChallengesView;

import java.util.ArrayList;

public class ChallengesViewStub implements ChallengesView {

    private String username;
    private String toastMessage;
    private String clock;
    private boolean usernameChanged;
    private int startDualActivityClick;
    private ArrayList<QuizChallenge> myChallenges;
    private ArrayList<QuizChallenge> incomingChallenges;
    private ListView myChallengesListView;
    private ListView incomingChallengesListView;


    /**
     * Επιστέφει το όνομα χρήστη
     * @return Όνομα Χρήστη
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Επιστέφει την λίστα των δικών μου προκλήσεων
     * @return Λίστα δικών μου προκλήσεων
     */
    @Override
    public ListView getMyChallenges() {
        return myChallengesListView;
    }

    /**
     * Επιστέφει την λίστα των προκλήσεων που έχω δεχτεί
     * @return Λίστα των προκλήσεων που έχω δεχτεί
     */
    @Override
    public ListView getIncomingChallenges() {
        return incomingChallengesListView;
    }

    /**
     * Βάζει null τιμή στην λίστα των δικών μου προκλήσεων
     */
    public void setMyChallengesListView() {
        myChallengesListView = null;
    }

    /**
     * Βάζει null τιμή στην λίστα των προκλήσεων που έχω δεχτεί
     */
    public void setIncomingChallengesListView() {
        incomingChallengesListView = null;
    }

    /**
     * Επιστέφει την λίστα των δικών μου προκλήσεων
     * @return Λίστα δικών μου προκλήσεων
     */
    public ArrayList<QuizChallenge> getMyChallengesList() {
        return myChallenges;
    }

    /**
     * Επιστέφει την λίστα των προκλήσεων που έχω δεχτεί
     * @return Λίστα των προκλήσεων που έχω δεχτεί
     */
    public ArrayList<QuizChallenge> getIncomingChallengesList() {
        return incomingChallenges;
    }

    /**
     * Επιστέφει τον χρόνο του ρολογιού
     * @return Το χρόνο
     */
    @Override
    public String getClock() {
        return clock;
    }

    /**
     * Βάζει τιμή στην λίστα με τις προκλήσεις που έχω δημιουργήσει
     * @param myChallenges Η λίστα με τις προκλήσεις
     */
    @Override
    public void setMyChallenges(ArrayList<QuizChallenge> myChallenges) {
        this.myChallenges = myChallenges;
    }

    /**
     * Βάζει τιμή στην λίστα με τις προκλήσεις που μου έχουν κάνει
     * @param incomingChallenges Η λίστα με τις προκλήσεις
     */
    @Override
    public void setIncomingChallenges(ArrayList<QuizChallenge> incomingChallenges) {
        this.incomingChallenges = incomingChallenges;
    }

    /**
     * Ορίζει τιμή στο χρόνο του ρολογιού
     * @param time Ο χρόνος που ορίζει
     */
    @Override
    public void setClock(String time) {
        this.clock = time;
    }

    /**
     * Βάζει true στο usernameChanged εάν άλλαξε το όνομα
     */
    @Override
    public void setUsername() {
        usernameChanged = true;
    }

    /**
     * Επιστρέφει true έαν έχει αλλάξει το όνομα
     * @return Εάν το όνομα άλλαξε
     */
    public Boolean getUsernameChanged() {
        return usernameChanged;
    }

    /**
     * Βάζει τιμή στο όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Ξεκινάει το dual Activity
     * @param challengeId Το μοναδικό id της πρόκλησης
     */
    @Override
    public void startDualActivity(int challengeId) {
        startDualActivityClick++;
    }

    /**
     * Επιστρέφει το πλήθος που ξεκινήσαμε το dual activity
     * @return Το πλήθος που ξεκινήσαμε το dual activity
     */
    public int getStartDualActivityClick() {
        return startDualActivityClick;
    }

    /**
     * Εμφανίζει το μήνυμα στην οθόνη του χρήστη
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String value) {
        this.toastMessage = value;
    }

    /**
     * Επιστρέφει το μήνυμα της οθόνης
     * @return Το μήνυμα της οθόνης
     */
    public String getToastMessage() {
        return toastMessage;
    }
}
