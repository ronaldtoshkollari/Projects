package com.example.quiz.memorydao;

import com.example.quiz.dao.QuizParticipationDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.QuizParticipation;

import java.util.ArrayList;
import java.util.List;

public class QuizParticipationDAOMemory implements QuizParticipationDAO {

    protected static ArrayList<QuizParticipation> entities = new ArrayList<QuizParticipation>();

    /**
     * Διαγράφει μια συμμετοχή
     * @param quizParticipation Η συμμετοχή
     */
    @Override
    public void delete(QuizParticipation quizParticipation) {
        entities.remove(quizParticipation);
    }

    /**
     * Επιστρέφει την λίστα με τις συμμετοχές
     * @return Τη λίστα με τις συμμετοχές
     */
    @Override
    public List<QuizParticipation> findAll() {
        ArrayList<QuizParticipation> result = new ArrayList<QuizParticipation>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει μια συμμετοχή
     * @param quizParticipation Η συμμετοχή
     */
    @Override
    public void save(QuizParticipation quizParticipation) {
        entities.add(quizParticipation);
    }

    /**
     * Επιστρέφει μια συμμετοχή με βαση το κωδικό της
     * @param quizParticipationId Ο κωδικός της συμμετοχής
     * @return Μια συμμετοχή
     */
    @Override
    public QuizParticipation find(int quizParticipationId) {
        for(QuizParticipation answer : entities)
            if(answer.getQuizParticipationId() == quizParticipationId)
                return answer;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά την τελευταία πρόκληση
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getQuizParticipationId()+1 : 1);
    }
}
