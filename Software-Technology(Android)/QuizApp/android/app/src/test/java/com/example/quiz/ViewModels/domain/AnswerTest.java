package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.Answer;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnswerTest {

    @Test
    public void memberFunctions(){
        Answer answer = new Answer();

        answer.setCorrect(false);
        assertFalse(answer.isCorrect());

//        answer.setDescription("Pass?");
//        assertEquals("Pass?", answer.getDescription());
//
//        Answer answer1 = new Answer("OK?", true );
//        assertEquals("OK?", answer1.getDescription());
//        assertEquals(true, answer1.isCorrect());


    }

}