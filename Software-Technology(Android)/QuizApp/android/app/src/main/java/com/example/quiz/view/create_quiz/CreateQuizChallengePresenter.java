package com.example.quiz.view.create_quiz;

import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.quiz.dao.PlayerDao;
import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.dao.QuizDAO;
import com.example.quiz.domain.DateTime;
import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;

import java.util.ArrayList;

public class CreateQuizChallengePresenter {

    private QuizChallengeDAO quizChallengeDAO;
    private QuizDAO quizDAO;
    private PlayerDao players;
    private CreateQuizChallengeView createQuizChallengeView;

    private Player opponent;
    private Player initiator;
    private QuizCategory quizCategory;
    private DifficultyLevel difficultyLevel;
    private QuizChallenge quizChallenge;


    /**
     * Φτιάχνει τον Presenter για να εφαρμοστούν
     * αλλαγές αργότερα
     * @param createQuizChallengeView
     * @param quizChallengeDAO
     * @param quizDAO
     * @param players
     */
    public CreateQuizChallengePresenter(CreateQuizChallengeView createQuizChallengeView, QuizChallengeDAO quizChallengeDAO, QuizDAO quizDAO, PlayerDao players) {
        this.createQuizChallengeView = createQuizChallengeView;
        this.players = players;
        this.quizChallengeDAO = quizChallengeDAO;
        this.quizDAO = quizDAO;
    }


    /**
     * Φτιάχνει μια πρόκληση
     * @return True εάν υπάρχει ο παίκτης, false εαν δεν υπάρχει
     */
    public boolean initializeQuizChallenge() {
        opponent = players.find(createQuizChallengeView.getOpponent());
        initiator = players.find(createQuizChallengeView.getUsername());

        if (opponent == null) {
            createQuizChallengeView.showToast("Player not found.");
            return false;
        }//end of if

        String quizCategory = createQuizChallengeView.getCategory();
        String difficultyLevel = createQuizChallengeView.getDifficultyLevel();
        Quiz quiz = new Quiz(quizDAO.nextId(), new DateTime(), 0,  new ArrayList<Question>());

        quizChallenge = new QuizChallenge(quizChallengeDAO.nextId(), quiz, initiator, opponent, new DateTime(), new DifficultyLevel(difficultyLevel), new QuizCategory(quizCategory));
        quizChallengeDAO.save(quizChallenge);

        return true;

    }//end of initializeQuizChallenge

    /**
     * Ξεκινάει το Activity με τις προκλήσεις
     */
    public void startChallenges() {
        createQuizChallengeView.startChallengesActivity();
    }

}
