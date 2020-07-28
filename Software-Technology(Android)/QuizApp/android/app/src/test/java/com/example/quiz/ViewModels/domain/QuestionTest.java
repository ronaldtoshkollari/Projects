package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.QuizCategory;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void memberFunctions(){
        Question question = new Question();

        DifficultyLevel difficultyLevel = new DifficultyLevel("Easy");
        question.setLevel(difficultyLevel);
        assertEquals("Easy", question.getLevel().getDifficultyLevel());

        QuizCategory quizCategory = new QuizCategory("Sports");
        question.setCategory(quizCategory);
        assertEquals("Sports", question.getCategory().getCategory());

        question.setQuestion("Passed?");
        assertEquals("Passed?", question.getQuestion());

    }

}