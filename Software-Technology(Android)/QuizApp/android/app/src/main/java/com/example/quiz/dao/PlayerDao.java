package com.example.quiz.dao;


import com.example.quiz.domain.Player;
import java.util.List;

public interface  PlayerDao {

    /**
     * Διαγράφει έναν παίκτη
     * @param player Ο παίκτης
     */
    void delete(Player player);

    /**
     * Επιστρέφει όλους τους παίκτες.
     * @return Οι παίκτες
     */
    List<Player> findAll();

    /**
     * Αποθηκεύει έναν παίκτη.
     * @param player Ο παίκτης
     */
    void save(Player player);

    /**
     * Βρίσκει έναν παίκτη με βάση το όνομα χρήστη του.
     * @param username Το όνομα του χρήστη
     * @return Ο παίκτης που βρέθηκε ή null
     */
    Player find(String username);

    /**
     * Επιστρέφει τον επόμενο κωδικό που μπορεί να αποδοθεί σε έναν παίκτη.
     * @return Ο κωδικός του παίκτη
     */
    int nextId();


}
