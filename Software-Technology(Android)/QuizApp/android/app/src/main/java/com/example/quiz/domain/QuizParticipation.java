package com.example.quiz.domain;

import java.util.List;

public class QuizParticipation {

    private int quizParticipationId;
    private Quiz quiz;
    private Player player;
    private List<Answer> answers;
    private int secondsPlayed;
    private int correctAnswers;
    private boolean winner;

    /**
     * Κατασκευαστής
     */
    public QuizParticipation(){};

    /**
     * Κατασκευαστής
     * @param quizParticipationId Το id της συμμετοχής σε ένα quiz
     * @param quiz Το quiz
     * @param player Ο παίκτης
     * @param answers Η λίστα με τις απαντήσεις
     * @param secondsPlayed Η συνολική διάρκεια απάντησης των ερωτήσεων σε δευτερόλεπτα
     * @param correctAnswers Η σωστή απάντηση
     * @param winner Εάν είναι νικητής
     */
    public QuizParticipation(int quizParticipationId, Quiz quiz, Player player, List<Answer> answers, int secondsPlayed, int correctAnswers, boolean winner) {
        this.quizParticipationId = quizParticipationId;
        this.quiz = quiz;
        this.player = player;
        this.answers = answers;
        this.secondsPlayed = secondsPlayed;
        this.correctAnswers = correctAnswers;
        this.winner = winner;
    }

    /**
     * Επιστρέφει το id της συμμετοχής σε ένα quiz
     * @return Tο id της συμμετοχής σε ένα quiz
     */
    public int getQuizParticipationId() {
        return quizParticipationId;
    }

    public void setQuizParticipationId(int quizParticipationId) {
        this.quizParticipationId = quizParticipationId;
    }

    /**
     * Επιστρέφει το quiz
     * @return Tο quiz
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Επιστρέφει το quiz
     * @param quiz Tο quiz
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Επιστρέφει τον παίκτη
     * @return Tον παίκτη
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Επιστρέφει τον παίκτη
     * @param player Tον παίκτη
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Επιστρέφει τη λίστα με τις απαντήσεις
     * @return Tη λίστα με τις απαντήσεις
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Επιστρέφει τη λίστα με τις απαντήσεις
     * @param answers Tη λίστα με τις απαντήσεις
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Επιστρέφει τη συνολική διάρκεια απάντησης των ερωτήσεων σε δευτερόλεπτα
     * @return Tη συνολική διάρκεια απάντησης των ερωτήσεων σε δευτερόλεπτα
     */
    public int getSecondsPlayed() {
        return secondsPlayed;
    }

    /**
     * Επιστρέφει τη συνολική διάρκεια απάντησης των ερωτήσεων σε δευτερόλεπτα
     * @param secondsPlayed Tη συνολική διάρκεια απάντησης των ερωτήσεων σε δευτερόλεπτα
     */
    public void setSecondsPlayed(int secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
    }

    /**
     * Επιστρέφει τη σωστή απάντηση
     * @return Tη σωστή απάντηση
     */
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Επιστρέφει τη σωστή απάντηση
     * @param correctAnswers Tη σωστή απάντηση
     */
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Επιστρέφει το εάν είναι νικητής
     * @return Tο εάν είναι νικητής
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * Επιστρέφει το εάν είναι νικητής
     * @param winner Tο εάν είναι νικητής
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
