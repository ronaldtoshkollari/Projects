package com.example.quiz.dao;


import com.example.quiz.domain.Quiz;

import java.util.List;

public interface QuizDAO {


    /**
     * Διαγράφει ένα Quiz
     * @param quiz Το Quiz
     */
    void delete(Quiz quiz);

    /**
     * Επιστρέφει όλες τα Quiz.
     * @return Τα Quiz
     */
    List<Quiz> findAll();

    /**
     * Αποθηκεύει ένα Quiz.
     * @param quiz Το Quiz
     */
    void save(Quiz quiz);

    /**
     * Βρίσκει ένα Quiz με βάση τον κωδικό του.
     * @param quizId Ο κωδικός του Quiz
     * @return Το Quiz που βρέθηκε ή null
     */
    Quiz find(int quizId);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε ένα Quiz.
     * @return Ο κωδικός του Quiz
     */
    int nextId();

}
