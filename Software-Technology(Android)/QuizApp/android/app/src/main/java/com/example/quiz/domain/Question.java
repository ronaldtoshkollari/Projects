package com.example.quiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int questionId;
    private QuizCategory category;
    private DifficultyLevel level;
    private String question;
    private String correctAnswer;
    private ArrayList<String> wrongAnswers;

    /**
     * Κατασκευαστής
     */
    public Question(){}

    /**
     * Κατασκευαστής
     * @param questionId Το id της ερώτησης
     * @param category Η κατηγορία της ερώτησης
     * @param level Το επίπεδο δυσκολίας της ερώτησης
     * @param question Η ερώτηση
     * @param correctAnswer Η σωστή απάντηση
     * @param wrongAnswers Η λίστα με τις λάθος απαντήσεις
     */
    public Question(int questionId, QuizCategory category, DifficultyLevel level, String question, String correctAnswer, ArrayList<String> wrongAnswers) {
        this.questionId = questionId;
        this.category = category;
        this.level = level;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    /**
     * Επιστρέφει το id της ερώτησης
     * @return Το id της ερώτησης
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Θέτει το id της ερώτησης
     * @param questionId Το id της ερώτησης
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Επιστρέφει την κατηγορία της ερώτησης
     * @return Η κατηγορία της ερώτησης
     */
    public QuizCategory getCategory() {
        return category;
    }

    /**
     * Θέτει την κατηγορία της ερώτησης
     * @param category Η κατηγορία της ερώτησης
     */
    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    /**
     * Επιστρέφει το επίπεδο δυσκολίας της ερώτησης
     * @return Το επίπεδο δυσκολίας της ερώτησης
     */
    public DifficultyLevel getLevel() {
        return level;
    }

    /**
     * Θέτει το επίπεδο δυσκολίας της ερώτησης
     * @param level Το επίπεδο δυσκολίας της ερώτησης
     */
    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }

    /**
     * Επιστρέφει την ερώτηση
     * @return Η ερώτηση
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Θέτει την ερώτηση
     * @param question Η ερώτηση
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Επιστρέφει την σωστή απάντηση
     * @return Η σωστή απάντηση
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Θέτει την σωστή απάντηση
     * @param correctAnswer Η σωστή απάντηση
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Επιστρέφει την λίστα με τις λάθος απαντήσσεις
     * @return Η λίστα με τις λάθος απαντήσσεις
     */
    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }

    /**
     * Θέτει την λίστα με τις λάθος απαντήσσεις
     * @param wrongAnswers Η λίστα με τις λάθος απαντήσσεις
     */
    public void setWrongAnswers(ArrayList<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
