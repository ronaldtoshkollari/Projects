package com.example.quiz.domain;

public class Answer {

    private int answerId;
    private Question question;
    private String answer;
    private boolean correct;

    /**
     * Κατασκευαστής
     */
    public Answer() {
    }


    /**
     * Φτιάχνει το αντικέιμενο της απάντησης
     * @param answerId Το id της απάντησης
     * @param question Η ερώτηση
     * @param answer Η απάντηση
     * @param correct Εάν είναι σωστή η απάντηση
     */
    public Answer(int answerId, Question question, String answer, boolean correct) {
        this.answerId = answerId;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
    }

    /**
     * Επιστρέφει το κωδικό της απάντησης
     * @return Ο κωδικός
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * Βάζει τιμή στον κωδικό της απάντησης
     * @param answerId Ο κωδικός της απάντησης
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    /**
     * Επιστρέφει την ερώτηση για την οποία δόθηκε η απάντηση
     * @return Την ερώτηση
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Βάζει τιμή στην ερώτηση
     * @param question Η ερώτηση
     */
    public void setQuestion(Question question) {
        this.question = question;
    }


    /**
     * Επιστρέφει την απάντηση
     * @return Την απάντηση
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Βάζει τιμή στην απάντηση
     * @param answer Η απάντηση
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Επιστρέφει έαν η απάντηση είναι σωστή
     * @return Εαν η απάντηση είναι σωστή
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Βάζει τιμή στην μεταβλητή που δηλώνει την ορθότητα της απάντησης
     * @param correct Εαν η απάντηση είναι σωστή
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
