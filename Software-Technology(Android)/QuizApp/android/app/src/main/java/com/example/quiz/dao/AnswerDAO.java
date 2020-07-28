package com.example.quiz.dao;

import com.example.quiz.domain.Answer;

import java.util.List;


public interface AnswerDAO {


    /**
     * Διαγράφει μια απάντηση
     * @param answer Η απάντηση
     */

    void delete(Answer answer);


    /**
     * Επιστρέφει όλες τις απαντήσεις.
     * @return Οι απαντήσεις
     */
    List<Answer> findAll();


    /**
     * Αποθηκεύει μια απάντηση.
     * @param answer Η απάντηση
     */
    void save(Answer answer);

    /**
     * Βρίσκει μια απάντηση με βάση τον κωδικό της.
     * @param answerId Ο κωδικός της απάντησης
     * @return Η απάντηση που βρέθηκε ή null
     */
    Answer find(int answerId);


    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία απάντηση.
     * @return Ο κωδικός της απάντησης
     */
    int nextId();
}
