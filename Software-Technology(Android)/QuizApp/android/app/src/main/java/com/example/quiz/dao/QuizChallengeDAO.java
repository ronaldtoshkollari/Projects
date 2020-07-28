package com.example.quiz.dao;


import com.example.quiz.domain.QuizChallenge;

import java.util.List;


public interface QuizChallengeDAO {

    /**
     * Διαγράφει μια πρόκληση
     * @param quizChallenge Η πρόκληση
     */
    void delete(QuizChallenge quizChallenge);

    /**
     * Επιστρέφει όλες τις προκλήσεις.
     * @return Οι προκλήσεις
     */
    List<QuizChallenge> findAll();

    /**
     * Αποθηκεύει μια πρόκληση.
     * @param quizChallenge Η πρόκληση
     */
    void save(QuizChallenge quizChallenge);

    /**
     * Βρίσκει μια πρόκληση με βάση τον κωδικό της.
     * @param quizChallengeId Ο κωδικός της πρόκλησης
     * @return Η πρόκληση που βρέθηκε ή null
     */
    QuizChallenge find(int quizChallengeId);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία πρόκληση.
     * @return Ο κωδικός της πρόκληση
     */
    int nextId();
}
