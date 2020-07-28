package com.example.quiz.ViewModels.view.dual;

import com.example.quiz.dao.Initializer;
import com.example.quiz.memorydao.AnswerDAOMemory;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.memorydao.QuizParticipationDAOMemory;
import com.example.quiz.view.dual.DualPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DualPresenterTest {

    Initializer dataHelper;
    DualViewStub view;
    DualPresenter presenter;

    /**
     * Δημιουργία δεδομένων για τα τέστ
     */
    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new DualViewStub();
        presenter = new DualPresenter(view, new PlayerDAOMemory(), new QuestionDAOMemory(), new QuizChallengeDAOMemory(), new QuizDAOMemory(), new QuizParticipationDAOMemory(), new AnswerDAOMemory());
    }

    /**
     * Έλεγχος get/set μεθόδων για το username
     */
    @Test
    public void usernameTest() {
        Assert.assertNull(view.getUsername());
        view.setUsername("test");
        Assert.assertEquals("test", view.getUsername());
    }

    /**
     * Έλεγχος get/set μεθόδων για το quizChallengeId
     */
    @Test
    public void quizChallengeIdTest() {
        Assert.assertEquals(0, view.getQuizChallengeId());
        view.setQuizChallengeId(1);
        Assert.assertEquals(1, view.getQuizChallengeId());
    }

    /**
     * Έλεγχος get/set μεθόδων για το question
     */
    @Test
    public void questionTest() {
        Assert.assertNull(view.getQuestion());
        view.setQuestion("question");
        Assert.assertEquals("question", view.getQuestion());
    }

    /**
     * Έλεγχος get/set μεθόδων για το answer1
     */
    @Test
    public void answer1Test() {
        Assert.assertNull(view.getAnswer1());
        view.setAnswer1("answer1");
        Assert.assertEquals("answer1", view.getAnswer1());
    }

    /**
     * Έλεγχος get/set μεθόδων για το answer2
     */
    @Test
    public void answer2Test() {
        Assert.assertNull(view.getAnswer2());
        view.setAnswer2("answer2");
        Assert.assertEquals("answer2", view.getAnswer2());
    }

    /**
     * Έλεγχος get/set μεθόδων για το answer3
     */
    @Test
    public void answer3Test() {
        Assert.assertNull(view.getAnswer3());
        view.setAnswer3("answer3");
        Assert.assertEquals("answer3", view.getAnswer3());
    }

    /**
     * Έλεγχος get/set μεθόδων για το answer4
     */
    @Test
    public void answer4Test() {
        Assert.assertNull(view.getAnswer4());
        view.setAnswer4("answer4");
        Assert.assertEquals("answer4", view.getAnswer4());
    }

    /**
     * Έλεγχος get/set μεθόδων για το clock
     */
    @Test
    public void clockTest(){
        Assert.assertNull(view.getClock());
        view.setClock("20");
        Assert.assertEquals("20", view.getClock());
    }

    /**
     * Έλεγχος get/set μεθόδων για το count
     */
    @Test
    public void countTest(){
        Assert.assertEquals(0, view.getCount());
        view.addCount();
        Assert.assertEquals(1, view.getCount());
    }

    /**
     * Ελέγχει την αρχικοποίηση μιας μάχης Quiz, δηλαδή τις ερωτήσεις
     */
    @Test
    public void initDualTest(){
        view.setQuizChallengeId(1);
        presenter.initDual();
        Assert.assertEquals(20, presenter.getQuestions().size());
    }

    /**
     * Ελέγχει την αρχικοποίηση της επόμενης ερώτησης
     */
    @Test
    public void getNextQuestionTest(){
        view.setQuizChallengeId(1);
        presenter.initDual();
        presenter.getNextQuestion(1);

        Assert.assertNotNull(view.getQuestion());
        Assert.assertNotNull(view.getAnswer1());
        Assert.assertNotNull(view.getAnswer2());
        Assert.assertNotNull(view.getAnswer3());
        Assert.assertNotNull(view.getAnswer4());
    }

    /**
     * Ελέγχει την καταχώρηση μίας ερώτησης
     */
    @Test
    public void submitQuestionTest(){
        view.setQuizChallengeId(1);
        presenter.initDual();

        presenter.submitQuestion(1, 0);
        Assert.assertNotNull(dataHelper.getAnswerDAO().find(41));

        presenter.submitQuestion(2, 1);
        Assert.assertNotNull(dataHelper.getAnswerDAO().find(42));
    }

    /**
     * Ελέγχει τον υπολογισμό του τελικού σκορ του username
     */
    @Test
    public void calculateScoreTest(){

        view.setQuizChallengeId(1);
        view.setUsername("roni");
        presenter.initDual();
        presenter.calculateScore();

        Assert.assertNotNull(dataHelper.getQuizParticipationDAO().find(3));

    }

    /**
     * Ελέγχος για την εμφάνιση στον χρήστου του νικητή του quiz
     */
    @Test
    public void getWinnerTest() {
        view.setQuizChallengeId(1);
        view.setUsername("roni");

        //Initiator is the winner

        presenter.calculateScore();
        presenter.getWinner();
        Assert.assertEquals("The winner is stelios", view.getToastMessage());



        //Opponent is the winner
        dataHelper.getAnswerDAO().find(4).setCorrect(false);
        dataHelper.getAnswerDAO().find(21).setCorrect(true);
        dataHelper.getQuizParticipationDAO().find(1).setCorrectAnswers(2);
        dataHelper.getQuizParticipationDAO().find(2).setCorrectAnswers(1);

        presenter.calculateScore();
        presenter.getWinner();
        Assert.assertEquals("The winner is " + view.getUsername(), view.getToastMessage());


        //same answers
        dataHelper.getAnswerDAO().find(4).setCorrect(true);
        dataHelper.getQuizParticipationDAO().find(1).setCorrectAnswers(2);
        dataHelper.getQuizParticipationDAO().find(2).setCorrectAnswers(2);
        presenter.calculateScore();
        presenter.getWinner();
        Assert.assertEquals("The winner is roni", view.getToastMessage());

    }
}
