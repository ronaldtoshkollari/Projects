package com.example.quiz.ViewModels.dao;

import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.dao.Initializer;
import com.example.quiz.dao.PlayerDao;
import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.dao.QuizDAO;
import com.example.quiz.dao.QuizParticipationDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.DateTime;
import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.domain.QuizParticipation;
import com.example.quiz.memorydao.AnswerDAOMemory;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.memorydao.QuizParticipationDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DAOTest {

    private AnswerDAO answerDAO;
    private PlayerDao playerDao;
    private QuestionDAO questionDAO;
    private QuizChallengeDAO quizChallengeDAO;
    private QuizDAO quizDAO;
    private QuizParticipationDAO quizParticipationDAO;

    private static final int TOTAL_PLAYERS = 4;
    private static final int TOTAL_QUESTIONS = 40;
    private static final int TOTAL_QUIZ_CHALLENGE = 2;
    private static final int TOTAL_QUIZ = 2;
    private static final int TOTAL_ANSWERS = 40;
    private static final int TOTAL_PARTICIPATIONS = 2;

    /**
     * Φτιάχνουμε τα δεδομένα
     */
    @Before
    public void setUp() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        answerDAO = new AnswerDAOMemory();
        playerDao = new PlayerDAOMemory();
        questionDAO = new QuestionDAOMemory();
        quizChallengeDAO = new QuizChallengeDAOMemory();
        quizDAO = new QuizDAOMemory();
        quizParticipationDAO = new QuizParticipationDAOMemory();
    }

    /**
     * Ελέγχουμε το μέγεθος του PlayerDAO
     */
    @Test
    public void findPlayersSize() {
        Assert.assertEquals(4, playerDao.findAll().size());
    }

    /**
     * Ελέγχουμε το μέγεθος του AnswerDAO
     */
    @Test
    public void findAnswersSize() {
        Assert.assertEquals(40, answerDAO.findAll().size());
    }

    /**
     * Ελέγχουμε το μέγεθος του QuestionDAO
     */
    @Test
    public void findQuestionsSize() {
        Assert.assertEquals(40, questionDAO.findAll().size());
    }

    /**
     * Ελέγχουμε το μέγεθος του QuizChallengeDAO
     */
    @Test
    public void findQuizChallengesSize() {
        Assert.assertEquals(2, quizChallengeDAO.findAll().size());
    }

    /**
     * Ελέγχουμε το μέγεθος του QuizDAO
     */
    @Test
    public void findQuizSize() {
        Assert.assertEquals(2, quizDAO.findAll().size());
    }

    /**
     * Ελέγχουμε το μέγεθος του QuizParticipationDAO
     */
    @Test
    public void findQuizParticipationsSize() {
        Assert.assertEquals(2, quizParticipationDAO.findAll().size());
    }

    //Find Objects Tests

    /**
     * Ελέγχουμε την ύπαρξη ενός παίκτη
     */
    @Test
    public void findPlayer() {
        Assert.assertNotNull(playerDao.find("roni"));
    }

    /**
     * Ελέγχουμε την ύπαρξη μιας απάντησης
     */
    @Test
    public void findAnswer() {
        Assert.assertNotNull(answerDAO.find(3));
    }

    /**
     * Ελέγχουμε την ύπαρξη μιας ερώτησης
     */
    @Test
    public void findQuestion() {
        Assert.assertNotNull(questionDAO.find(3));
    }

    /**
     * Ελέγχουμε την ύπαρξη μιας πρόκλησης
     */
    @Test
    public void findQuizChallenge() {
        Assert.assertNotNull(quizChallengeDAO.find(1));
    }

    /**
     * Ελέγχουμε την ύπαρξη ενός Quiz
     */
    @Test
    public void findQuiz() {
        Assert.assertNotNull(quizDAO.find(1));
    }

    /**
     * Ελέγχουμε την ύπαρξη μιας συμμετοχής
     */
    @Test
    public void findQuizParticipation() {
        Assert.assertNotNull(quizParticipationDAO.find(1));
    }

    //Save Objects Tests

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση ενός παίκτη
     */
    @Test
    public void savePlayer() {
        Player player = new Player(playerDao.nextId(), "marko10", "aek", "marko10@gmail.com", "marko", "livaja", "male", new QuizCategory("Sports"));
        playerDao.save(player);

        Assert.assertEquals(TOTAL_PLAYERS + 1, playerDao.findAll().size());
        Assert.assertNotNull(playerDao.find(player.getUsername()));
        Assert.assertTrue(playerDao.findAll().contains(player));
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση μιας απάντησης
     */
    @Test
    public void saveAnswer() {
        Answer answer = new Answer(answerDAO.nextId(), questionDAO.find(1), "ARIS", false);
        answerDAO.save(answer);

        Assert.assertEquals(TOTAL_ANSWERS + 1, answerDAO.findAll().size());
        Assert.assertNotNull(answerDAO.find(answer.getAnswerId()));
        Assert.assertTrue(answerDAO.findAll().contains(answer));
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση μιας ερώτησης
     */
    @Test
    public void saveQuestion() {
        Question question = new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which is the best team in Premier League?", "Manchester United", new ArrayList<String>());
        questionDAO.save(question);

        Assert.assertEquals(TOTAL_QUESTIONS + 1, questionDAO.findAll().size());
        Assert.assertNotNull(questionDAO.find(question.getQuestionId()));
        Assert.assertTrue(questionDAO.findAll().contains(question));
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση μιας πρόκλησης
     */
    @Test
    public void saveQuizChallenge() {
        QuizChallenge quizChallenge = new QuizChallenge(quizChallengeDAO.nextId(), new Quiz(), playerDao.find("marko10"), playerDao.find("stelios"), true, new DateTime(), new DifficultyLevel("Easy"), new QuizCategory("Sports"));
        quizChallengeDAO.save(quizChallenge);

        Assert.assertEquals(TOTAL_QUIZ_CHALLENGE + 1, quizChallengeDAO.findAll().size());
        Assert.assertNotNull(quizChallengeDAO.find(quizChallenge.getQuizChallengeId()));
        Assert.assertTrue(quizChallengeDAO.findAll().contains(quizChallenge));
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση ενός Quiz
     */
    @Test
    public void saveQuiz() {
        Quiz quiz = new Quiz(quizDAO.nextId(), new DateTime(), 0, new ArrayList<Question>());
        quizDAO.save(quiz);

        Assert.assertEquals(TOTAL_QUIZ + 1, quizDAO.findAll().size());
        Assert.assertNotNull(quizDAO.find(quiz.getQuizId()));
        Assert.assertTrue(quizDAO.findAll().contains(quiz));
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η αποθήκευση μιας συμμετοχής
     */
    @Test
    public void saveQuizParticipation() {
        QuizParticipation quizParticipation = new QuizParticipation(quizParticipationDAO.nextId(), quizDAO.find(1), playerDao.find("marko10"), new ArrayList<Answer>(), 0, 0, false);
        quizParticipationDAO.save(quizParticipation);

        Assert.assertEquals(TOTAL_PARTICIPATIONS + 1, quizParticipationDAO.findAll().size());
        Assert.assertNotNull(quizParticipationDAO.find(quizParticipation.getQuizParticipationId()));
        Assert.assertTrue(quizParticipationDAO.findAll().contains(quizParticipation));
    }

    //Delete Object Test
    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή ενός παίκτη
     */
    @Test
    public void deletePlayer(){
        Player player = playerDao.findAll().get(1);
        playerDao.delete(player);
        Assert.assertEquals(TOTAL_PLAYERS - 1, playerDao.findAll().size());
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή μιας απάντησης
     */
    @Test
    public void deleteAnswer(){
        Answer answer = answerDAO.findAll().get(1);
        answerDAO.delete(answer);
        Assert.assertEquals(TOTAL_ANSWERS - 1, answerDAO.findAll().size());
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή μιας ερώτησης
     */
    @Test
    public void deleteQuestion(){
        Question question = questionDAO.findAll().get(1);
        questionDAO.delete(question);
        Assert.assertEquals(TOTAL_QUESTIONS - 1, questionDAO.findAll().size());
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή μιας πρόκλησης
     */
    @Test
    public void deleteQuizChallenge(){
        QuizChallenge quizChallenge = quizChallengeDAO.findAll().get(1);
        quizChallengeDAO.delete(quizChallenge);
        Assert.assertEquals(TOTAL_QUIZ_CHALLENGE - 1, quizChallengeDAO.findAll().size());
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή ενός Quiz
     */
    @Test
    public void deleteQuiz(){
        Quiz quiz = quizDAO.findAll().get(1);
        quizDAO.delete(quiz);
        Assert.assertEquals(TOTAL_QUIZ - 1, quizDAO.findAll().size());
    }

    /**
     * Ελέγχουμε εάν γίνεται σωστά η διαγραφή μιας συμμετοχής
     */
    @Test
    public void deleteQuizParticipation(){
        QuizParticipation quizParticipation = quizParticipationDAO.findAll().get(1);
        quizParticipationDAO.delete(quizParticipation);
        Assert.assertEquals(TOTAL_PARTICIPATIONS - 1, quizParticipationDAO.findAll().size());
    }




}
