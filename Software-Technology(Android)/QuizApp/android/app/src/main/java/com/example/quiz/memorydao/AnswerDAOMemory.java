package com.example.quiz.memorydao;

import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.domain.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerDAOMemory implements AnswerDAO {

    protected static ArrayList<Answer> entities = new ArrayList<Answer>();

    /**
     * Διαγράφει μια απάντηση
     * @param answer Η απάντηση
     */
    @Override
    public void delete(Answer answer) {
        entities.remove(answer);
    }

    /**
     * Επιστρέφει την λίστα με τις απαντήσεις
     * @return Τη λίστα με τις απαντήσεις
     */
    @Override
    public List<Answer> findAll() {
        ArrayList<Answer> result = new ArrayList<Answer>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει μια απάντηση
     * @param answer Η απάντηση
     */
    @Override
    public void save(Answer answer) {
        entities.add(answer);
    }

    /**
     * Επιστρέφει μια απάντηση με βαση το κωδικό της
     * @param answerId Ο κωδικός της απάντησης
     * @return Μια απάντηση
     */
    @Override
    public Answer find(int answerId) {
        for(Answer answer : entities)
            if(answer.getAnswerId() == answerId)
                return answer;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά την τελευταία απάντηση
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getAnswerId()+1 : 1);
    }
}
