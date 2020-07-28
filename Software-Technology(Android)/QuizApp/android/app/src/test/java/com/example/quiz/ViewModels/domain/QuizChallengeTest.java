package com.example.quiz.ViewModels.domain;


import com.example.quiz.domain.DateTime;
import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.domain.QuizChallenge;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuizChallengeTest {

    @Test
    public void testDate() {
        //QuizChallenge quizChallenge = new QuizChallenge(false, null,null);

        //assertTrue(new DateTime().compareTo(quizChallenge.getDate()) <= 0); //the challenge created at the past
    }

    @Test
    public void memberFunctions(){
        QuizChallenge quizChallenge = new QuizChallenge();

        quizChallenge.setAccepted(true);
        assertTrue(quizChallenge.isAccepted());

        DateTime dateTime = new DateTime(24, 4, 2020, 11, 39, 45);
        quizChallenge.setDate(dateTime);

        assertEquals("24-4-2020-11-39-45", quizChallenge.getDate().getDateTime());

        DifficultyLevel difficultyLevel = new DifficultyLevel("Easy");
        quizChallenge.setLevel(difficultyLevel);

        assertEquals("Easy", quizChallenge.getLevel().getDifficultyLevel());

        QuizCategory quizCategory = new QuizCategory("Sports");
        quizChallenge.setCategory(quizCategory);

        assertEquals("Sports", quizChallenge.getCategory().getCategory());

//        Player player = new Player("Jim","Beq","jimisbek@hotmail.gr","Jim","Beq","male","Geography",false);
//
//        quizChallenge.setInitiator(player);
//        assertEquals("jimisbek@hotmail.gr",quizChallenge.getInitiator().getEmail());
//
//        quizChallenge.setParticipant(player);
//        assertEquals("jimisbek@hotmail.gr",quizChallenge.getParticipant().getEmail());
    }
}
