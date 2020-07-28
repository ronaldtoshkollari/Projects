package com.example.quiz.memorydao;

import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAOMemory implements QuestionDAO {

    protected static ArrayList<Question> entities = new ArrayList<Question>();

    /**
     * Διαγράφει μια ερώτηση
     * @param question Η ερώτηση
     */
    @Override
    public void delete(Question question) {
        entities.remove(question);
    }

    /**
     * Επιστρέφει την λίστα με τις ερωτήσεις
     * @return Τη λίστα με τις ερωτήσεις
     */
    @Override
    public List<Question> findAll() {
        ArrayList<Question> result = new ArrayList<Question>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει μια ερώτηση
     * @param question Η ερώτηση
     */
    @Override
    public void save(Question question) {
        entities.add(question);
    }

    /**
     * Επιστρέφει μια ερώτηση με βαση το κωδικό της
     * @param questionId Ο κωδικός της ερώτησης
     * @return Μια ερώτηση
     */
    @Override
    public Question find(int questionId) {
        for(Question question : entities)
            if(question.getQuestionId() == questionId)
                return question;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά την τελευταία ερώτηση
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getQuestionId()+1 : 1);
    }
}
