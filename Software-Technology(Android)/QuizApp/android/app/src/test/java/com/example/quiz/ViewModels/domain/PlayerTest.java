package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.Answer;
import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.domain.QuizParticipation;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testLogInOutAndAPI() {
//        Player player = new Player("Jim","Beq","jimisbek@hotmail.gr","Jim","Beq","male","Geography",false);
//
//        assertTrue(player.logIn("Jim","Beq"));
//        assertTrue(player.isOnline());
//
//        assertTrue(player.logOut("Jim","Beq"));
//        assertFalse(player.isOnline());
    }

    @Test
    public void memberFunctions(){
        Player player = new Player();

        player.setFirstName("Roni");
        assertEquals("Roni", player.getFirstName());

        player.setLastName("Tosko");
        assertEquals("Tosko", player.getLastName());

        player.setGender("Male");
        assertEquals("Male", player.getGender());

//        player.setFavoriteCategory("Sports");
//        assertEquals("Sports", player.getFavoriteCategory());

        player.setUsername("ronito");
        assertEquals("ronito", player.getUsername());

        player.setPassword("12345");
        assertEquals("12345", player.getPassword());

        player.setEmail("roni@email.com");
        assertEquals("roni@email.com", player.getEmail());

//        player.setOnline(true);
//        assertEquals(true, player.isOnline());
    }

    @Test
    public void quizDualTest() {
//        Player p1 = new Player("Jim","Beq","jimisbek@hotmail.gr","Jim","Beq","male","Geography",true);
//        Player p2 = new Player("Roni","Tosko","roni@hotmail.gr","Roni","Tosko","male","Sports",true);
//
//        QuizChallenge quizChallenge = p2.rejectQuizChallenge(p1,p1.createQuizChallenge("Sports","Medium",p2));
//        assertFalse(quizChallenge.isAccepted());
//
//        quizChallenge = p2.acceptQuizChallenge(p1,p1.createQuizChallenge("Sports","Medium",p2));
//        assertTrue(quizChallenge.isAccepted());
//
//        //create hard code questions with answers
//        Question q1 = new Question(new QuizCategory("Sports"),new DifficultyLevel("Medium"),"Q1");
//        q1.addAnswer(new Answer("A1",false));
//        q1.addAnswer(new Answer("A2",true));
//
//        Question q2 = new Question(new QuizCategory("Sports"),new DifficultyLevel("Medium"),"Q2");
//        q2.addAnswer(new Answer("A3",true));
//        q2.addAnswer(new Answer("A4",false));


//        Quiz quiz = p1.createQuiz(quizChallenge);
//        quiz.addQuestion(q1);
//        quiz.addQuestion(q2);
//
//        QuizParticipation qp1 = new QuizParticipation(0,0,false);
//        QuizParticipation qp2 = new QuizParticipation(0,0,false);
//
//        p1.sendAnswer(new Answer("A1",false),0,quiz,qp1);
//        p1.sendAnswer(new Answer("A3",false),1,quiz,qp1);
//
//        p2.sendAnswer(new Answer("A2",false),0,quiz,qp2);
//        p2.sendAnswer(new Answer("A3",false),1,quiz,qp2);
//
//        assertEquals(1,qp1.getCorrectAnswers());
//        assertEquals(2,qp2.getCorrectAnswers());
    }
}