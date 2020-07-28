package com.example.quiz.ViewModels.view.challenges;

import com.example.quiz.domain.Question;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.service.QuestionService;

import java.util.ArrayList;

public class QuestionServiceStub implements QuestionService {

    private ArrayList<Question> questions;
    private String category;
    private String difficultyLvl;
    private String msg;

    /**
     * Διαβάζει τα μηνύματα απο το API
     */
    @Override
    public void questionReader() {
        msg = "In async task";
    }

    /**
     * Ορίζει την κατηγορία
     * @param category Η κατηγορία
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Ορίζει το επίπεδο δυσκολίας
     * @param difficultyLvl Το επίπεδο δυσκολίας
     */
    @Override
    public void setDifficultyLvl(String difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    /**
     * Επιστρέφει την λίστα των ερωτήσεων
     * @return Τις ερωτήσεις
     */
    @Override
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Ορίζει την λίστα των ερωτήσεων
     * @param questions Η λίστα των ερωτήσεων
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
