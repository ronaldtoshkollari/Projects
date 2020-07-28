package com.example.quiz.memorydao;

import com.example.quiz.dao.PlayerDao;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAOMemory implements PlayerDao {

    protected static ArrayList<Player> entities = new ArrayList<Player>();

    /**
     * Διαγράφει έναν παίκτη
     * @param player Ο παίκτης
     */
    @Override
    public void delete(Player player) {
        entities.remove(player);
    }

    /**
     * Επιστρέφει την λίστα με τους παίκτες
     * @return Τη λίστα με τους παίκτες
     */
    @Override
    public List<Player> findAll() {
        ArrayList<Player> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει έναν παίκτη
     * @param player Ο παίκτης
     */
    @Override
    public void save(Player player) {
        entities.add(player);
    }

    /**
     * Επιστρέφει έναν παίκτη με βαση το κωδικό του
     * @param username Το όνομα του χρήστη
     * @return Έναν παίκτη
     */
    @Override
    public Player find(String username) {
        for(Player player : entities)
            if(player.getUsername().equals(username.trim()))
                return player;

        return null;
    }

    /**
     * Επιστρέφει τον κωδικό μετά τον τελευταίο παίκτη
     * @return Ο επόμενος κωδικός
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getPlayerId()+1 : 1);
    }
}
