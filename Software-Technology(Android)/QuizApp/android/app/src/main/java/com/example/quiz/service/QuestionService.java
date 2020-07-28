package com.example.quiz.service;

import com.example.quiz.domain.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {

    void questionReader();
    void setCategory(String category);
    void setDifficultyLvl(String difficultyLvl);
    ArrayList<Question> getQuestions();

}
