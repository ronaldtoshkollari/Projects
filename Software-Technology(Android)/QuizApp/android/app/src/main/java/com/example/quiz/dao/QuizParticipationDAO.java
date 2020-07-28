package com.example.quiz.dao;

import com.example.quiz.domain.QuizParticipation;

import java.util.List;

public interface QuizParticipationDAO {

    /**
     * Διαγράφει μια συμμετοχή
     * @param quizParticipation Η συμμετοχή
     */
    void delete(QuizParticipation quizParticipation);

    /**
     * Επιστρέφει όλες τις συμμετοχές.
     * @return Οι συμμετοχές
     */
    List<QuizParticipation> findAll();

    /**
     * Αποθηκεύει μια συμμετοχή.
     * @param quizParticipation Η συμμετοχή
     */
    void save(QuizParticipation quizParticipation);

    /**
     * Βρίσκει μια συμμετοχή με βάση τον κωδικό της.
     * @param quizParticipationId Ο κωδικός της συμμετοχής
     * @return Η συμμετοχή που βρέθηκε ή null
     */
    QuizParticipation find(int quizParticipationId);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε μία συμμετοχή.
     * @return Ο κωδικός της συμμετοχή
     */
    int nextId();

}
