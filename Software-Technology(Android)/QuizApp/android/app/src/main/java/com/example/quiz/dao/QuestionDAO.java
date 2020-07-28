package com.example.quiz.dao;

import com.example.quiz.domain.Question;

import java.util.List;

public interface QuestionDAO {

    /**
     * Διαγράφει μια ερώτηση
     * @param question Η ερώτηση
     */
    void delete(Question question);

    /**
     * Επιστρέφει όλες τις ερώτησεις.
     * @return Οι ερώτησεις
     */
    List<Question> findAll();

    /**
     * Αποθηκεύει μια ερώτηση.
     * @param question Η ερώτηση
     */
    void save(Question question);

    /**
     * Βρίσκει μια ερώτηση με βάση τον κωδικό της.
     * @param questionId Ο κωδικός της ερώτησης
     * @return Η ερώτηση που βρέθηκε ή null
     */
    Question find(int questionId);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία ερώτηση.
     * @return Ο κωδικός της ερώτηση
     */
    int nextId();

}
