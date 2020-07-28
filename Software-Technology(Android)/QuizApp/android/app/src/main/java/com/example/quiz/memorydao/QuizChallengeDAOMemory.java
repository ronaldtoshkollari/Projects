package com.example.quiz.memorydao;

import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.QuizChallenge;

import java.util.ArrayList;
import java.util.List;

public class QuizChallengeDAOMemory implements QuizChallengeDAO {

    protected static ArrayList<QuizChallenge> entities = new ArrayList<QuizChallenge>();

    /**
     * Διαγράφει μια πρόκληση
     * @param quizChallenge Η πρόκληση
     */
    @Override
    public void delete(QuizChallenge quizChallenge) {
        entities.remove(quizChallenge);
    }

    /**
     * Επιστρέφει την λίστα με τις προκλήσεις
     * @return Τη λίστα με τις προκλήσεις
     */
    @Override
    public List<QuizChallenge> findAll() {
        ArrayList<QuizChallenge> result = new ArrayList<QuizChallenge>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει μια πρόκληση
     * @param quizChallenge Η πρόκληση
     */
    @Override
    public void save(QuizChallenge quizChallenge) {
        entities.add(quizChallenge);
    }

    /**
     * Επιστρέφει μια πρόκληση με βαση το κωδικό της
     * @param quizChallengeId Ο κωδικός της πρόκλησης
     * @return Μια πρόκληση
     */
    @Override
    public QuizChallenge find(int quizChallengeId) {
        for(QuizChallenge quizChallenge : entities)
            if(quizChallenge.getQuizChallengeId() == quizChallengeId)
                return quizChallenge;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά την τελευταία πρόκληση
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getQuizChallengeId()+1 : 1);
    }
}
