package com.example.quiz.memorydao;

import com.example.quiz.dao.QuizDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDAOMemory implements QuizDAO {

    protected static ArrayList<Quiz> entities = new ArrayList<Quiz>();

    /**
     * Διαγράφει ένα quiz
     * @param quiz Το Quiz
     */
    @Override
    public void delete(Quiz quiz) {
        entities.remove(quiz);
    }

    /**
     * Επιστρέφει την λίστα με τα quiz
     * @return Τη λίστα με τα quiz
     */
    @Override
    public List<Quiz> findAll() {
        ArrayList<Quiz> result = new ArrayList<Quiz>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει ένα quiz
     * @param quiz Το Quiz
     */
    @Override
    public void save(Quiz quiz) {
        entities.add(quiz);
    }

    /**
     * Επιστρέφει ένα quiz με βαση το κωδικό της
     * @param quizId Ο κωδικός του Quiz
     * @return Το Quiz
     */
    @Override
    public Quiz find(int quizId) {
        for(Quiz quiz : entities)
            if(quiz.getQuizId() == quizId)
                return quiz;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά to τελευταίo quiz
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getQuizId()+1 : 1);
    }
}
