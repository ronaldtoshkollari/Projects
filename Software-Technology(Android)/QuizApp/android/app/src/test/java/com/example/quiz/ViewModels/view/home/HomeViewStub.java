package com.example.quiz.ViewModels.view.home;

import com.example.quiz.view.home.HomeView;

public class HomeViewStub implements HomeView {

    private String username;
    private String toastMessage;
    private int startCreateQuizActivityClick;
    private int startChallengesActivityClick;
    private int startShowRankActivityClick;

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
     */
    @Override
    public void setUsername() {

    }

    /**
     * Θέτει το όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Αυξάνει τα click για το activity CreateQuizActivity κατά ένα
     */
    @Override
    public void startCreateQuizActivity() {
        startCreateQuizActivityClick++;
    }

    /**
     * Αυξάνει τα click για το activity ChallengesActivity κατά ένα
     */
    @Override
    public void startChallengesActivity() {
        startChallengesActivityClick++;
    }

    /**
     * Αυξάνει τα click για το activity ShowRankActivity κατά ένα
     */
    @Override
    public void startShowRankActivity() {
        startShowRankActivityClick++;
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
     * Επιστρέφει το πλήθος των clicks για το activity CreateQuizActivity
     */
    public int getStartCreateQuizActivityClick() {
        return startCreateQuizActivityClick;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity ChallengesActivity
     */
    public int getStartChallengesActivityClick() {
        return startChallengesActivityClick;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity ShowRankActivity
     */
    public int getStartShowRankActivityClick() {
        return startShowRankActivityClick;
    }
}
