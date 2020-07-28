package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.DateTime;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class QuizTest {

    @Test
    public void testDateAndDuration() {
        Quiz quiz = new Quiz(new DateTime(),0,new LinkedList<Question>());

        assertTrue(quiz.getStartDate().compareTo(new DateTime()) <= 0); //the quiz has been created at the past

        quiz.addDuration(10);
        assertTrue(quiz.getDuration() >= 0);
    }

    @Test
    public void memberFunctions(){
        Quiz quiz = new Quiz();

        quiz.setQuizId(1);
        assertEquals(1, quiz.getQuizId());



        quiz.addDuration(1000);
        assertEquals(1000, quiz.getDuration());

        DateTime date = new DateTime(24, 4, 2020, 11, 51, 23);
        quiz.setStartDate(date);
        assertEquals("24-4-2020-11-51-23", quiz.getStartDate().getDateTime());
    }
}
