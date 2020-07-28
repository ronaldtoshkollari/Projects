package com.example.quiz.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int quizId;
    private DateTime startDate;
    private int duration;

    private List<Question> questions;

    /**
     * Κατασκευαστής
     */
    public Quiz(){ this.questions = new ArrayList<>(); }

    /**
     * Κατασκευαστής
     * @param quizId Το id του quiz
     * @param startDate Ημερομηνία και ώρα έναρξης του quiz
     * @param duration Διάρκεια quiz
     * @param questions Ερωτήσεις quiz
     */
    public Quiz(int quizId, DateTime startDate, int duration, List<Question> questions) {
        this.quizId = quizId;
        this.startDate = startDate;
        this.duration = duration;
        this.questions = questions;
    }

    /**
     * Κατασκευαστής
     * @param startDate Ημερομηνία και ώρα έναρξης του quiz
     * @param duration Διάρκεια quiz
     * @param questions Ερωτήσεις quiz
     */
    public Quiz(DateTime startDate, int duration, List<Question> questions) {
        this.startDate = startDate;
        this.duration = duration;
        this.questions = questions;
    }

    /**
     * Επιστρέφει το id του quiz
     * @return Το id του quiz
     */
    public int getQuizId() {
        return quizId;
    }

    /**
     * Θέτει το id του quiz
     * @param quizId Το id του quiz
     */
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    /**
     * Επιστρέφει την Ημερομηνία και ώρα έναρξης του quiz
     * @return Την Ημερομηνία και ώρα έναρξης του quiz
     */
    public DateTime getStartDate() {
        return startDate;
    }

    /**
     * Θέτει την Ημερομηνία και ώρα έναρξης του quiz
     * @param startDate Την Ημερομηνία και ώρα έναρξης του quiz
     */
    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Επιστρέφει την διάρκεια του quiz
     * @return Την διάρκεια του quiz
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Αθροίζει την διάρκεια του quiz
     * @param duration Η διάρκεια απάντησης μίας ερώτησης
     */
    public void addDuration(int duration) {
        this.duration += duration;
    }

    /**
     * Επιστρέφει τις ερωτήσεις του quiz
     * @return Tις ερωτήσεις του quiz
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Θέτει τις ερωτήσεις του quiz
     * @param questions Οι ερωτήσεις του quiz
     */
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    /**
     * Προσθέτει μία ερώτηση στη λίστα με τις ερωτήσεις του quiz
     * @param question Η ερώτηση που προστίθενται
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }


}
