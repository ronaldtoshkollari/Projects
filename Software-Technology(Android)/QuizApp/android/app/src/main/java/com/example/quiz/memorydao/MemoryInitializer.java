package com.example.quiz.memorydao;

import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.dao.Initializer;
import com.example.quiz.dao.PlayerDao;
import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.dao.QuizDAO;
import com.example.quiz.dao.QuizParticipationDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.domain.QuizParticipation;

public class MemoryInitializer extends Initializer {

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    @Override
    protected void eraseData() {
        for (Player player: getPlayerDAO().findAll())
            getPlayerDAO().delete(player);

        for(Answer answer: getAnswerDAO().findAll())
            getAnswerDAO().delete(answer);

        for(Quiz quiz: getQuizDAO().findAll())
            getQuizDAO().delete(quiz);

        for(Question question: getQuestionDAO().findAll())
            getQuestionDAO().delete(question);

        for(QuizParticipation quizParticipation: getQuizParticipationDAO().findAll())
            getQuizParticipationDAO().delete(quizParticipation);

        for(QuizChallenge quizChallenge: getQuizChallengeDAO().findAll())
            getQuizChallengeDAO().delete(quizChallenge);

    }

    /**
     * Επιστρέφει το DAO των παικτών.
     * @return Το DAO των παικτών
     */
    @Override
    public PlayerDao getPlayerDAO() {
        return new PlayerDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των απαντήσεων.
     * @return Το DAO των απαντήσεων
     */
    @Override
    public AnswerDAO getAnswerDAO() {
        return new AnswerDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των Quiz.
     * @return Το DAO των Quiz
     */
    @Override
    public QuizDAO getQuizDAO() {
        return new QuizDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των ερωτήσεων.
     * @return Το DAO των ερωτήσεων
     */
    @Override
    public QuestionDAO getQuestionDAO() {
        return new QuestionDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των προκλήσεων.
     * @return Το DAO των προκλήσεων
     */
    @Override
    public QuizChallengeDAO getQuizChallengeDAO() {
        return new QuizChallengeDAOMemory();
    }

    /**
     * Επιστρέφει το DAO των συμμετοχών.
     * @return Το DAO των συμμετοχών
     */
    @Override
    public QuizParticipationDAO getQuizParticipationDAO() {
        return new QuizParticipationDAOMemory();
    }
}
