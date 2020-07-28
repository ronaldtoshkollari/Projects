package com.example.quiz.ViewModels.view.create_quiz;

import com.example.quiz.view.create_quiz.CreateQuizChallengeView;

public class CreateQuizChallengeViewStub implements CreateQuizChallengeView {

    private String opponent;
    private String difficultyLevel;
    private String category;
    private String username;
    private String toastMessage;
    private int startChallengesActivityClick;
    private boolean usernameChanged;

    /**
     * Επιστέφει το όνομα χρήστη του αντιπάλου
     * @return Το όνομα χρήστη του αντιπάλου
     */
    @Override
    public String getOpponent() {
        return opponent;
    }

    /**
     * Επιστέφει το επίπεδο δυσκολίας των ερωτήσεων
     * @return Το επίπεδο δυσκολίας των ερωτήσεων
     */
    @Override
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Επιστέφει την κατηγορία των ερωτήσεων
     * @return Την κατηγορία των ερωτήσεων
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Επιστέφει το όνομα χρήστη
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Θέτει το όνομα χρήστη του αντιπάλου
     * @param opponent Το επίπεδο δυσκολίας των ερωτήσεων
     */
    @Override
    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    /**
     * Θέτει το επίπεδο δυσκολίας των ερωτήσεων του quiz
     * @param difficultyLevel Το επίπεδο δυσκολίας των ερωτήσεων του quiz
     */
    @Override
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Θέτει την κατηφορία των ερωτήσεων του quiz
     * @param category Η κατηγορία των ερωτήσεων του quiz
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Θέτει true αν το όνομα χρήστη τροποποιήθηκε
     */
    @Override
    public void setUsername() {
        usernameChanged = true;
    }

    /**
     * Θέτει το όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Αυξάνει τα click για το activity ChallengesActivity κατά ένα
     */
    @Override
    public void startChallengesActivity() {
        startChallengesActivityClick++;
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String value) {
        this.toastMessage = value;
    }

    /**
     * Επιστρέφει ένα Toast.
     * @return Το περιεχόμενο που θα εμφανιστεί
     */
    public String getToastMessage() {
        return toastMessage;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity ChallengesActivity
     */
    public int getStartChallengesActivityClick() {
        return startChallengesActivityClick;
    }

    /**
     * Επιστρέφει εάν το όνομα χρήστη τροποποιήθηκε
     * @return Εάν το όνομα χρήστη τροποποιήθηκε
     */
    public boolean isUsernameChanged() {
        return usernameChanged;
    }
}
