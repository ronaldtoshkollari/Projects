package com.example.quiz.ViewModels.view.dual;

import com.example.quiz.view.dual.DualView;

public class DualViewStub implements DualView {


    private String username;
    private int quizChallengeId;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String clock;
    private int count;
    private String toastMessage;

    /**
     * Επιστέφει το όνομα χρήστη
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Επιστέφει το id μίας πρόκλησης για ένα quiz
     * @return Το id μίας πρόκλησης για ένα quiz
     */
    @Override
    public int getQuizChallengeId() {
        return quizChallengeId;
    }

    /**
     * Επιστέφει την τρέχων ερώτηση
     * @return Την τρέχων ερώτηση
     */
    @Override
    public String getQuestion() {
        return question;
    }

    /**
     * Επιστέφει την απάντηση 1 για την τρέχων ερώτηση
     * @return Την απάντηση 1 για την τρέχων ερώτηση
     */
    @Override
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Επιστέφει την απάντηση 2 για την τρέχων ερώτηση
     * @return Την απάντηση 2 για την τρέχων ερώτηση
     */
    @Override
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Επιστέφει την απάντηση 3 για την τρέχων ερώτηση
     * @return Την απάντηση 3 για την τρέχων ερώτηση
     */
    @Override
    public String getAnswer3() {
        return answer3;
    }

    /**
     * Επιστέφει την απάντηση 4 για την τρέχων ερώτηση
     * @return Την απάντηση 4 για την τρέχων ερώτηση
     */
    @Override
    public String getAnswer4() {
        return answer4;
    }

    /**
     * Επιστέφει τον εναπομείναντα χρόνο για την απάντηση της τρέχων ερώτησης
     * @return Τον εναπομείναντα χρόνο για την απάντηση της τρέχων ερώτησης
     */
    @Override
    public String getClock() {
        return clock;
    }

    /**
     * Επιστέφει το πλήθος των ερωτήσεων που έχουν απαντηθεί
     * @return Το πλήθος των ερωτήσεων που έχουν απαντηθεί
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Θέτει το όνομα χρήστη
     */
    @Override
    public void setUsername() {
        username = "test";
    }

    /**
     * Θέτει το όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Θέτει το id μίας πρόκλησης για ένα quiz
     * @param quizChallengeId Το id μίας πρόκλησης για ένα quiz
     */
    public void setQuizChallengeId(int quizChallengeId) {
        this.quizChallengeId = quizChallengeId;
    }

    /**
     * Θέτει την τρέχων ερώτηση
     * @param q Την τρέχων ερώτηση
     */
    @Override
    public void setQuestion(String q) {
        this.question = q;
    }

    /**
     * Θέτει την απάντηση 1 για την τρέχων ερώτηση
     * @param a1 Την απάντηση 1 για την τρέχων ερώτηση
     */
    @Override
    public void setAnswer1(String a1) {
        this.answer1 = a1;
    }

    /**
     * Θέτει την απάντηση 2 για την τρέχων ερώτηση
     * @param a2 Την απάντηση 2 για την τρέχων ερώτηση
     */
    @Override
    public void setAnswer2(String a2) {
        this.answer2 = a2;
    }

    /**
     * Θέτει την απάντηση 3 για την τρέχων ερώτηση
     * @param a3 Την απάντηση 3 για την τρέχων ερώτηση
     */
    @Override
    public void setAnswer3(String a3) {
        this.answer3 = a3;
    }

    /**
     * Θέτει την απάντηση 4 για την τρέχων ερώτηση
     * @param a4 Την απάντηση 4 για την τρέχων ερώτηση
     */
    @Override
    public void setAnswer4(String a4) {
        this.answer4 = a4;
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
     * Θέτει τον εναπομείναντα χρόνο για την απάντηση της τρέχων ερώτησης
     * @param time Τον εναπομείναντα χρόνο για την απάντηση της τρέχων ερώτησης
     */
    @Override
    public void setClock(String time) {
        this.clock = time;
    }

    /**
     * Αυξάνει το πλήθος των ερωτήσεων που απαντήθηκαν κατά ένα
     */
    @Override
    public void addCount() {
        this.count++;
    }
}
